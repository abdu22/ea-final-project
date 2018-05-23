package edu.mum.ea.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ea.domain.Task;
import edu.mum.ea.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {
	@Autowired
	private TaskService taskService;

	@PostMapping("/save")
	public Task save(@RequestBody Task task) {
		return taskService.save(task);
	}

	@PostMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		taskService.delete(id);
	}

	@GetMapping("/findById/{id}")
	public Task findById(@PathVariable("id") int id) {
		return taskService.findById(id);
	}

	@GetMapping("/findAll")
	public List<Task> findAll() {
		return taskService.findAll();
	}

}
