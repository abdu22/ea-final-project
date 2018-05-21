package edu.mum.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.ea.domain.User;
import edu.mum.ea.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/user/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(@ModelAttribute("user") User user) {
		return "admin/user/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/admin/user/";
	}
}
