package edu.mum.ea.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.ea.config.SessionListener;
import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.ProjectStatusEnum;
import edu.mum.ea.domain.Role;
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
	
	@Autowired
	private SessionListener sessionListener;


	@ModelAttribute("projectStatuses")
	public List<ProjectStatusEnum> productTypes() {
		return Arrays.asList(ProjectStatusEnum.values());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		List<Role> roles = sessionListener.getUser().getRoles();
		
		for (Role role : roles) {
			if(role.getName().equalsIgnoreCase("PROJECT_MANAGER")) {
				model.addAttribute("projects", projectService.findAll());
			}
			
			if(role.getName().equalsIgnoreCase("DEVELOPER")) {
				model.addAttribute("projects", projectService.findByDeveloper(sessionListener.getUser().getId()));
			}
			
		}


		
		return "project/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(@ModelAttribute("project") Project project, Model model) {
		model.addAttribute("developers", userService.findByRole(2));
		return "project/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("project") Project project) {
		Set<User> users = project.getUsers();
		for (User user: users) {
			user.addProject(project);
		}
		projectService.save(project);
		return "redirect:/project/";
	}
	
	  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	  public String delete(@PathVariable("id") int id) {
	    projectService.delete(id);
	    return "redirect:/project/";
	  }
}
