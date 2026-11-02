package mangalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Главный класс, отсюда запускается всё приложение
// @SpringBootApplication говорит Spring искать наши классы (контроллеры, репозитории и т.д.)
// в этом пакете и во всех вложенных
@SpringBootApplication
public class MangalogApplication {

	public static void main(String[] args) {
		// запускает встроенный Tomcat на порту 8080
		SpringApplication.run(MangalogApplication.class, args);
	}

}
