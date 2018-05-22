package edu.mum.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.ea.config.SessionListener;
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

	@Autowired
	private SessionListener sessionListener;

	@GetMapping("/signup")
	public String signUp(Model model, @ModelAttribute("user") User user) {
		// set the default role for a new user
		user.addRole(roleService.getRole(2));
		user.setEnabled(true);
		return "account/signup";
	}

	@PostMapping("/signup")
	public String createNewAccount(Model model, @ModelAttribute("user") User user) {
		User existingUser = userService.findByEmail(user.getEmail());
		if (existingUser != null) {
			model.addAttribute("errorMsg", "This email already exists. Please use another email.");
		} else {
			user.setPassword(encoder.encode(user.getPassword()));
			userService.save(user);
			userService.sendEmail(user);
			model.addAttribute("infoMsg", "Your new account has been created sucessfully. Click here to login");
		}
		return "account/signup";
	}
	
	@GetMapping("/update")
	public String account(Model model) {
		User user = userService.findByEmail(sessionListener.getUser().getEmail());
		model.addAttribute("user", user);
		return "account/account";
	}

	@PostMapping("/update")
	public String updateAccount(Model model, @ModelAttribute("user") User user) {
		User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			user.setPassword(existingUser.getPassword());
		} else {
			user.setPassword(encoder.encode(user.getPassword()));
		}
		userService.save(user);
		return "redirect:/account/update";
	}
}
