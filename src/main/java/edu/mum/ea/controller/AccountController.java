package edu.mum.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.ea.domain.User;
import edu.mum.ea.service.RoleService;
import edu.mum.ea.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/account/signup")
	public String signUp(Model model, @ModelAttribute("user") User user) {
		// set the default role for a new user
		user.addRole(roleService.getRole(2));
		user.setEnabled(true);
		return "account/signup";
	}

	@PostMapping("/account/signup")
	public String createNewAccount(Model model, @ModelAttribute("user") User user) {
		User existingUser = userService.findByEmail(user.getEmail());
		if (existingUser != null) {
			model.addAttribute("errorMsg", "This email already exists. Please use another email.");
		} else {
			user.setPassword(encoder.encode(user.getPassword()));
			userService.save(user);
			model.addAttribute("infoMsg", "Your new account has been created sucessfully. Click here to login");
		}
		return "account/signup";
	}
}
