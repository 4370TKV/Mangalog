package mangalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Самый простой тест: проверяет, что приложение вообще запускается
// Внимание: для него нужна запущенная БД и переменная DB_PASSWORD, иначе упадёт
@SpringBootTest
class MangalogApplicationTests {

	@Test
	void contextLoads() {
		// тут пусто специально: если приложение запустилось без ошибок, тест пройден
	}

}
