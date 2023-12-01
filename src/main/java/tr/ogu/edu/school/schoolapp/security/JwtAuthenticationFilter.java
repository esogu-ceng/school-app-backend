package tr.ogu.edu.school.schoolapp.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tr.ogu.edu.school.schoolapp.util.JwtUtil;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtUtil.getSecretKey()).parseClaimsJws(token).getBody();

			String username = claims.getSubject();
			if (username != null) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						new ArrayList<>());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (JwtException e) {
			// FIXME:Bu kısma log eklenmelidir.
		}

		filterChain.doFilter(request, response);
	}
}
