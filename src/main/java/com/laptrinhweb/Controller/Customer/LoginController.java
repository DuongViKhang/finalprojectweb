package com.laptrinhweb.Controller.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhweb.Service.Implementation.ReCaptchaValidationService;
import com.laptrinhweb.Service.Implementation.UserDetailService;
import com.laptrinhweb.repository.LoginRepository;

@Controller
public class LoginController {
	@Autowired
	private LoginRepository userRepository;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private ReCaptchaValidationService validator;

	@GetMapping("/login")
	public String login(Authentication authentication) {
		if ((authentication != null))
			return "index";
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/registersave")
	public String saveUser(Authentication authentication, Model model, RedirectAttributes redirectAttributes,
			@RequestParam(name = "g-recaptcha-response") String captcha,
			@RequestParam(name = "fullName", required = false) String fullname,
			@RequestParam(name = "usernameNew", required = false) String username,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "phone", required = false) String phone,
			@RequestParam(name = "passwordNew", required = false) String password) {
		if (validator.validateCaptcha(captcha)) {
			String result = userDetailService.addNewUser(fullname, username, email, phone, password, "Nam");
			if (result.equals("success"))
				redirectAttributes.addFlashAttribute("messagesuccess", "Đăng kí người dùng thành công!!!");
			else
				redirectAttributes.addFlashAttribute("messagefail", "Đăng kí người dùng thất bại!!!");
		} else
			redirectAttributes.addFlashAttribute("messagefail", "Vui lòng xác thực bạn không phải robot!!!");
		return "redirect:/register";
	}

	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
}
