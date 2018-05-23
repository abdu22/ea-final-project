package edu.mum.ea.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.ea.config.SessionListener;
import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.Task;
import edu.mum.ea.domain.User;
import edu.mum.ea.service.ProjectService;
import edu.mum.ea.service.TaskService;
import edu.mum.ea.service.UserService;

@Controller
@RequestMapping("/project")
public class ProjectDetailController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private SessionListener sessionListener;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String showDetails(Model model, @PathVariable("id") int id, @ModelAttribute("task") Task task, @ModelAttribute("developer") User developer) {
		Project project = projectService.findById(id);
		List<User> unassignedDevelopers = userService.findByRole(2).stream().filter(d->!d.getProjects().contains(project)).collect(Collectors.toList());
		model.addAttribute("developers", unassignedDevelopers); //retrieve all developers
		model.addAttribute("project", project);
		model.addAttribute("tasks", project.getTasks());
		return "project-details";
	}
	
	@PostMapping("/developer/add")
	public String assignDeveloper(Model model, @ModelAttribute("developer") User developer,
			@RequestParam(value = "projectId", required = true) Integer projectId) {		
		Project project = projectService.findById(projectId);
		User user = userService.findById(developer.getId());
		project.getUsers().add(user);
		user.getProjects().add(project);
		userService.save(user);
		projectService.save(project);
		return "redirect:/project/details/" + projectId;
	}
	
	@RequestMapping("/developer/remove/{developerId}")
	public String unassignDeveloper(Model model, @PathVariable("developerId") Long developerId,
			@RequestParam(value = "projectId", required = true) Integer projectId) {
		Project project = projectService.findById(projectId);
		User developer = userService.findById(developerId);
		project.getUsers().remove(developer);
		developer.getProjects().remove(project);
		userService.save(developer);
		projectService.save(project);
		
		
		return "redirect:/project/details/" + projectId;
	}
	
	

	@PostMapping("/task/add")
	public String addTask(Model model, @ModelAttribute("task") Task task,
			@RequestParam(value = "projectId", required = true) Integer projectId) {
		User existingUser = userService.findByEmail(sessionListener.getUser().getEmail());
		task.setUser(existingUser);
		task.setDate(new Date());
		Project project = projectService.findById(projectId);
		task.setProject(project);
		List<Task> tasks = getAllTasks();
		if (tasks.size() > 0) {
			for (Task selectedTask : tasks) {
				project.getTasks().add(selectedTask);
			}
		}
		project.getTasks().add(task);
		projectService.save(project);
		return "redirect:/project/details/" + projectId;
	}

	@RequestMapping("/task/remove/{taskId}")
	public String removeTask(Model model, @PathVariable("taskId") Long taskId,
			@RequestParam(value = "projectId", required = true) Integer projectId) {
		Project project = projectService.findById(projectId);
		taskService.delete(taskId);
		for (Task selectedTask : project.getTasks()) {
			if (selectedTask.getId() == taskId) {
				projectService.removeTask(selectedTask);
				break;
			}
		}
		return "redirect:/project/details/" + projectId;
	}

	@ModelAttribute("allTasks")
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}
}