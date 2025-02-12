package hub.policy.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
        JwtAuthenticationFilter jwtAuthenticationFilter,
        AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.cors().and() // Explicitly Enable CORS
            .csrf().disable()
                .authorizeHttpRequests()
                // public endpoints
                .requestMatchers("/policy-hub/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v*/api-docs/**","/images/**")
                .permitAll()
                
                // Customer APIs (Require Authentication)
                .requestMatchers("/policy-hub/customer/**")
                .permitAll()
                
                // Admin APIs (Restricted to Admins)
                .requestMatchers("/policy-hub/admin/**")
                .permitAll()
                
                // Secure Other Endpoints (Authenticated Users)
                .requestMatchers(
                    "/policy-hub/user-policy/**",
                    "/policy-hub/payment/**",
                    "/policy-hub/user-kyc/**",
                    "/policy-hub/policy-type/**",
                    "/policy-hub/policy-plan/**",
                    "/policy-hub/claim/**"
                ).authenticated()
                
                // Any other request must be authenticated
                .anyRequest().authenticated()
                .and()
                
                // Stateless Session Management
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                
                // Set Authentication Provider
                .authenticationProvider(authenticationProvider)
                
                // Add JWT Filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

   
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // ✅ Allow Frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // ✅ Include OPTIONS for preflight
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // ✅ Fix allowed headers
        configuration.setExposedHeaders(List.of("Authorization")); // ✅ Expose JWT Token
        configuration.setAllowedOriginPatterns(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    
    

}


