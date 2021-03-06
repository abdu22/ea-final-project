package edu.mum.ea.service;

import java.util.List;

import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.User;

public interface UserService {

	public void save(User user);

	public List<User> findAll();

	public User findByEmail(String email);

	public User findById(long id);

	public void sendEmail(User user);

	public List<User> findByRole(long roleId);

	public void delete(long id);
	
	public List<User> findAvailableDevelopers(Project project);
}
