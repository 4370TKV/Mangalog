package mangalog.model;

// Пользователь из таблицы users
public class User {

	private Long id;
	private String username;
	// в базе лежит хеш, сам пароль мы нигде не храним для безопасности собственно
	private String passwordHash;

	public User(Long id, String username, String passwordHash) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

}
