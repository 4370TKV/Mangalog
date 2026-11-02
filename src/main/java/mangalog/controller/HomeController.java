package mangalog.controller;

import mangalog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Контроллер главной страницы
// Пока просто показывает, что приложение подключилось к БД
@Controller
public class HomeController {

	private final UserRepository userRepository;

	// Конструктор. Spring сам создаст UserRepository и передаст его сюда
	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Срабатывает, когда открываем http://localhost:8080/
	@GetMapping("/")
	public String index(Model model) {
		// Пока сервиса нет, для проверки связи с БД обращаемся к репозиторию напрямую
		// Слой service появится вместе с регистрацией
		long userCount = userRepository.count();

		// кладём число в модель, чтобы шаблон мог его показать
		model.addAttribute("userCount", userCount);

		// возвращаем имя шаблона, Spring найдёт файл templates/index.html
		return "index";
	}

}
