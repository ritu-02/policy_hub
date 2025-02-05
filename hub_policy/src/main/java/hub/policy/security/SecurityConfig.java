package hub.policy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
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
		http.cors(csrf -> csrf.disable())
		    .authorizeHttpRequests(
		    		auth -> auth
		    		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		    		.antMatchers(
		    				"/v*/api-doc*/**", //OpenAPI documentation
		    				"/swagger-ui/**", //Swagger UI
		    				"/swagger-resources/**", //Swagger resources
		    				"/webjars/**" //WebJars used by Swagger
		    ).permitAll()
		    		.antMatchers("/api/auth/login","/api/auth/register").permitAll() // public end points
		    		.antMatchers("/admin/**").hasRole("ADMIN") //Admin-specific endpoints
		    		.antMatchers("/customer/**").hasRole("CUSTOMER") //Customer - specific end points
		    		.anyRequest().authenticated()
		    )
		    .httpBasic(Customizer.withDefaults()) // enable optional HTTP Basic Authentication
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
		.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntry))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //Add JWT filter
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	

	
	

}
