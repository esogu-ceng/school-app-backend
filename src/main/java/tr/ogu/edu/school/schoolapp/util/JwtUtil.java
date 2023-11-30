package tr.ogu.edu.school.schoolapp.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tr.ogu.edu.school.schoolapp.model.User;

@Component
public class JwtUtil {

	private String secret = "school-app";

	public String generateToken(User user) {
		long now = System.currentTimeMillis();
		return Jwts.builder().setSubject(user.getMail()).setIssuedAt(new Date(now))
				.setExpiration(new Date(now + 900000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getSecretKey() {
		return secret;
	}

	public String extractUsername(String token) {
		return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody().getSubject();
	}
}
