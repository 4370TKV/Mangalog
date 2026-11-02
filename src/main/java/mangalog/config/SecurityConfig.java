package mangalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// ВРЕМЕННАЯ ЗАГЛУШКА настроек безопасности
// Сейчас пускает всех на все страницы без логина
// Потом заменить этот файл на нормальную конфигурацию с входом и регистрацией
@Configuration
public class SecurityConfig {

	// Правила доступа к страницам
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Читается так: для любого запроса (anyRequest) разрешить всем (permitAll)
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		return http.build();
	}

}
