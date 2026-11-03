package mangalog.controller;

import mangalog.service.RegistrationResult;
import mangalog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// страницы входа, регистрации и успешного входа
@Controller
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	// показывает форму входа и регистрации по шаблону login.html
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	// принимает форму регистрации. username и password - это name полей в форме
	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password) {
		RegistrationResult result = userService.register(username, password);

		// после POST делаем redirect, чтобы при обновлении страницы форма не отправилась ещё раз
		if (result == RegistrationResult.TAKEN) {
			return "redirect:/login?taken";
		}
		if (result == RegistrationResult.INVALID) {
			return "redirect:/login?invalid";
		}
		return "redirect:/login?registered";
	}

	// страница после успешного входа по шаблону success.html
	@GetMapping("/success")
	public String successPage() {
		return "success";
	}

}
