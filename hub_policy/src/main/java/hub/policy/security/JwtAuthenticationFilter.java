package hub.policy.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils utils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader=request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			
			String jwt = authHeader.substring(7);
			
			Claims payloadClaims = utils.validateJwtToken(jwt);
			
			String email=utils.getUserNameFromJwtToken(payloadClaims);
			
			List<GrantedAuthority> authorities = utils.getAuthoritiesFromClaims(payloadClaims);
			
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(email, null,authorities);
			
			SecurityContextHolder.getContext().setAuthentication(token);
			System.out.println("saved auth token in sec context");
		}
		filterChain.doFilter(request, response);
		
	}

}
