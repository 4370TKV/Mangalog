package mangalog.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
