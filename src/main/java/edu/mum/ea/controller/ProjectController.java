package edu.mum.ea.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.ProjectStatusEnum;
import edu.mum.ea.domain.User;
import edu.mum.ea.service.ProjectService;
import edu.mum.ea.service.UserService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;


	@ModelAttribute("projectStatuses")
	public List<ProjectStatusEnum> productTypes() {
		return Arrays.asList(ProjectStatusEnum.values());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("projects", projectService.findAll());
		return "project/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(@ModelAttribute("project") Project project, Model model) {
		model.addAttribute("developers", userService.findByRole(2));
		return "project/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("project") Project project) {
		
		projectService.save(project);
		return "redirect:/project/";
	}
}
