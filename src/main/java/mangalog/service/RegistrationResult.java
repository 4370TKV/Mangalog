package mangalog.service;

// окончание регистрации
public enum RegistrationResult {
	OK,       // пользователь создан
	TAKEN,    // такой логин уже есть
	INVALID   // пустой логин или пароль, слишком длинный логин или пароль
}
