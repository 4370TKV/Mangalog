package mangalog.service;

import mangalog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

// Бизнес-логика пользователей. Пока тут только регистрация
@Service
public class UserService {

	private final UserRepository userRepository;
	// шифратор BCrypt, он объявлен в SecurityConfig
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Регистрация нового пользователя и вывод того, чем всё закончилось
	public RegistrationResult register(String username, String password) {
		// пустые поля не пропускаем
		if (username.isBlank() || password.isBlank()) {
			return RegistrationResult.INVALID;
		}

		// в таблице под логин VARCHAR(50), тк длиннее база не примет
		if (username.length() > 50) {
			return RegistrationResult.INVALID;
		}

		// BCrypt не принимает пароль длиннее 72 байт, иначе будет ошибка 500
		// байты считаем в UTF-8: латинская буква = 1 байт, русская = 2
		if (password.getBytes(StandardCharsets.UTF_8).length > 72) {
			return RegistrationResult.INVALID;
		}

		// логин должен быть свободен
		if (userRepository.findByUsername(username) != null) {
			return RegistrationResult.TAKEN;
		}

		// в базу кладём не пароль, а его хеш
		String passwordHash = passwordEncoder.encode(password);
		userRepository.save(username, passwordHash);

		return RegistrationResult.OK;
	}

}
