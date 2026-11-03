package mangalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Настройки безопасности: кому какие страницы можно и как устроен вход
@Configuration
public class SecurityConfig {

	// Правила доступа к страницам и форма входа
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				// эти страницы открыты всем, даже без входа
				// /error - страница ошибки Spring Boot. Без неё гость при ошибке видел бы не ошибку, а /login
				.requestMatchers("/", "/login", "/register", "/error").permitAll()
				// всё остальное (сейчас это /success) - только после входа
				.anyRequest().authenticated()
		);

		// Вход делает сам Spring Security: принимает POST /login с полями username и password,
		// ищет пользователя через DbUserDetailsService и сверяет пароль с хешем
		http.formLogin(form -> form
				// наша страница с формой (GET /login в AuthController)
				.loginPage("/login")
				// после успешного входа всегда на /success
				.defaultSuccessUrl("/success", true)
				// при ошибке входа Spring Security сам отправит на /login?error
				// permitAll пускает всех на страницу входа и /login?error, иначе невошедший не увидит форму
				.permitAll()
		);

		return http.build();
	}

	// Шифратор паролей BCrypt. Один на всё приложение:
	// UserService хеширует им пароль при регистрации, Spring Security сверяет при входе
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
