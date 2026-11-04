package mangalog.repository;

import mangalog.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// Репозиторий для таблицы users
// Тут лежат все SQL-запросы к пользователям, пишем их руками через JdbcTemplate
@Repository
public class UserRepository {

	// JdbcTemplate - класс из Spring, через него выполняем SQL
	private final JdbcTemplate jdbcTemplate;

	// Конструктор. Spring сам создаст JdbcTemplate и передаст его сюда
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Считает, сколько всего пользователей в таблице
	public long count() {
		String sql = "SELECT COUNT(*) FROM users";

		// queryForObject выполняет запрос и возвращает одно значение (тут число)
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	// Ищет пользователя по логину. Если такого нет, возвращает null
	public User findByUsername(String username) {
		String sql = "SELECT id, username, password_hash FROM users WHERE username = ?";

		// вместо ? JdbcTemplate сам подставит username, так защищаемся от SQL-инъекций
		// (rs, rowNum) -> ... превращает строку из базы в объект User
		List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> new User(
				rs.getLong("id"),
				rs.getString("username"),
				rs.getString("password_hash")
		), username);

		// логин уникальный, поэтому в списке либо 0, либо 1 пользователь
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	// Сохраняет нового пользователя. id и created_at база заполнит сама
	public void save(String username, String passwordHash) {
		String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
		jdbcTemplate.update(sql, username, passwordHash);
	}

}
