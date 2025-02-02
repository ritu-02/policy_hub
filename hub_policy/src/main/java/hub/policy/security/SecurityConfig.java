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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
		    .csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authEntry)
		.and().authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.antMatchers("/users/login").hasRole("CUSTOMER")
		.antMatchers("/users/register").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	
	

}
