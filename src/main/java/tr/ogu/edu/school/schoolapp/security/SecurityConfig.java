package tr.ogu.edu.school.schoolapp.security;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import tr.ogu.edu.school.schoolapp.model.User;
import tr.ogu.edu.school.schoolapp.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	private final JwtUtil jwtUtil;

//	public SecurityConfig(JwtUtil jwtUtil) {
//		this.jwtUtil = jwtUtil;
//	}

	@Bean
	@Profile("dev")
	public SecurityFilterChain securityFilterChainDev(HttpSecurity http) throws Exception {
		http.csrf().and().cors(cors -> cors.disable()).authorizeHttpRequests().anyRequest().permitAll();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("admin", null,
				new ArrayList<>());
		AuthenticationService.devUser = new User();
		AuthenticationService.devUser.setId(1L);
		AuthenticationService.devUser.setName("Name");
		AuthenticationService.devUser.setSurname("Surname");
		AuthenticationService.devUser.setMail("Mail");
		AuthenticationService.devUser.setTckn("Tckn");
		AuthenticationService.devUser.setPassword("Password");
		return http.build();
	}

	@Bean
	@Profile("!dev")
	public SecurityFilterChain securityFilterChainProduction(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().configurationSource(corsConfigurationSource()).and()
				.authorizeHttpRequests((authz) -> authz.requestMatchers("/users/login", "/users", "/tickets/public/**")
						.permitAll().anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginProcessingUrl("/login").defaultSuccessUrl("/index.html"));
//				.and()
//				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter(jwtUtil);
//	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
