package mangalog.service;

import mangalog.model.User;
import mangalog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Объясняет Spring Security, что брать пользователей при входе нужно из таблицы users
// Пароль Spring Security сам сравнивает через BCrypt, мы только отдаём хеш
@Service
public class DbUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public DbUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Spring Security вызывает метод, когда кто-то нажимает "войти"
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		// если такого логина нет, Spring Security сам перекинет на /login?error
		if (user == null) {
			throw new UsernameNotFoundException("Пользователь не найден: " + username);
		}

		// User тут из Spring Security, а не наш поэтому пишем полное имя
		// ролей у нас пока нет, поэтому указываем только логин и хеш
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPasswordHash())
				.build();
	}

}
