package hub.policy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
	@Autowired
	private PasswordEncoder enc;
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
    private CustomAuthenticationEntryPoint authEntry;
	
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception{
		http.cors()
		    .and()
		    .csrf(csrf -> csrf.disable())
		    .authorizeHttpRequests(
		    		auth -> auth
		    		.antMatchers("/api/auth/**","/admin/**","/customer/**","/v*/api-doc*/**","/swagger-ui/**").permitAll()
		    		.antMatchers(HttpMethod.OPTIONS).permitAll()
		    		.antMatchers("/admin/**").hasRole("ADMIN")
		    		.antMatchers("/customer/**").hasRole("CUSTOMER")
		    		.anyRequest().authenticated()
		    )
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntry))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	

	
	

}
