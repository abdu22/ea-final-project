package edu.mum.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.mum.ea.service.ProjectService;

@Controller
public class HomeController {
	@Autowired
	private ProjectService projectService;

	@GetMapping({ "/", "/home" })
	public String getHomePage(Model model) {
		model.addAttribute("projects", projectService.findAll());
		return "index";
	}

}
