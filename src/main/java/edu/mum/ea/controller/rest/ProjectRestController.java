package edu.mum.ea.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ea.domain.Project;
import edu.mum.ea.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectRestController {
	@Autowired
	private ProjectService projectService;

	@PostMapping("/save")
	public Project save(@RequestBody Project project) {
		return projectService.save(project);
	}

	@PostMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		projectService.delete(id);
	}

	@GetMapping("/findById/{id}")
	public Project findById(@PathVariable("id") int id) {
		return projectService.findById(id);
	}

	@GetMapping("/findAll")
	public List<Project> findAll() {
		return projectService.findAll();
	}

}
