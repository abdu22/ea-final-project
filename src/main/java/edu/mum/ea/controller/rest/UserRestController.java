package edu.mum.ea.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ea.domain.User;
import edu.mum.ea.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public void save(@RequestBody User user) {
		userService.save(user);
	}

	@PostMapping("/delete/{id}")
	public void delete(@PathVariable("id") long id) {
		userService.delete(id);
	}

	@GetMapping("/findById/{id}")
	public User findById(@PathVariable("id") long id) {
		return userService.findById(id);
	}

	@GetMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();
	}

}
