package edu.mum.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.ea.domain.User;
import edu.mum.ea.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(@RequestParam("type") String type, Model model) {
		if(type.equalsIgnoreCase("dev")) 
			model.addAttribute("users", userService.findByRole(2));
		else 
			model.addAttribute("users", userService.findAll());
		
		return "user/index";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("id") Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "user/view";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(@ModelAttribute("user") User user) {
		return "user/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/user/";
	}
}
