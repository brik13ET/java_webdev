package univer.webdev.gettingstarted.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Value("${security.login}")
	List<String> Logins;

	@Value("${security.role}")
	List<String> Roles;

	@Value("${security.password}")
	List<String> Passwords;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
//				.formLogin(x -> x.defaultSuccessUrl("/projects", true))
				.httpBasic(Customizer.withDefaults())
				.formLogin( form -> form
						.defaultSuccessUrl("/projects", true)
						.permitAll()
				)
				.authorizeHttpRequests(x -> x
//						.requestMatchers(HttpMethod.GET,    "/**").authenticated()
//						.requestMatchers(HttpMethod.POST,   "/projectts/*/tasks/**").authenticated()
//						.requestMatchers(HttpMethod.PUT,    "/projectts/*/tasks/**").authenticated()
//						.requestMatchers(HttpMethod.DELETE, "/projectts/*/tasks/**").authenticated()
						.requestMatchers(HttpMethod.POST,   "/projects/*").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT,    "/projects/*").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/projects/*").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.build();
	}




	@Bean
	public UserDetailsService userDetailsService() {
		var ret = new InMemoryUserDetailsManager();
		for (int i = 0; i < Logins.size(); i++) {
			ret.createUser(User
					.withUsername(Logins.get(i))
					.password(passwordEncoder.encode(Passwords.get(i)))
					.roles(Roles.get(i))
					.build()
			);
		}
		return ret;
	}
}