package hub.policy.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
	
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	private Key getSigningKey() {
		byte[] keyBytes= Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	public String generateJwtToken(String email,String role) {
	   return Jwts.builder()
			      .setSubject(email)
			      .claim("role", role)
			      .setIssuedAt(new Date())
			      .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
			      .signWith(getSigningKey(), SignatureAlgorithm.HS256)
			      .compact();
	}


	public String extractEmail(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	 public String extractRole(String token) {
	        return extractAllClaims(token).get("role", String.class);
	 }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

	public boolean validateToken(String token, String email) {
		return extractEmail(token).equals(email) && !isTokenExpired(token);
	}
	

}
