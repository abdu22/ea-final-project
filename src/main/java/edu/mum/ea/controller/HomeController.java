package edu.mum.ea.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import edu.mum.ea.config.SessionListener;
import edu.mum.ea.domain.ProjectStatusEnum;
import edu.mum.ea.domain.Role;
import edu.mum.ea.service.ProjectService;
import edu.mum.ea.utils.SearchProjectParam;

@Controller
public class HomeController {
	@Autowired
	private ProjectService projectService;

	@Autowired
	private SessionListener sessionListener;

	@GetMapping({ "/", "/index", "/home", "/search" })
	public String getHomePage(Model model, @ModelAttribute("search") SearchProjectParam searchProjectParam) {
		List<Role> roles = new ArrayList<>();
		if (sessionListener.getUser() != null) {
			roles = sessionListener.getUser().getRoles();
		}
		if (roles != null) {
			for (Role role : roles) {
				if (role.getName().equalsIgnoreCase("PROJECT_MANAGER")) {
					model.addAttribute("projects", projectService.findAll());
				}

				if (role.getName().equalsIgnoreCase("DEVELOPER")) {
					model.addAttribute("projects", projectService.findByDeveloper(sessionListener.getUser().getId()));
				}

			}
		} else {
			model.addAttribute("projects", null);
		}
		return "index";
	}

	@PostMapping({ "/search" })
	public String search(Model model, @ModelAttribute("search") SearchProjectParam params) {
		model.addAttribute("projects", projectService.search(params));
		return "index";
	}

	@ModelAttribute("projectStatuses")
	public List<ProjectStatusEnum> productTypes() {
		return Arrays.asList(ProjectStatusEnum.values());
	}
}
