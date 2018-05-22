package edu.mum.ea.service;

import java.util.List;

import edu.mum.ea.domain.User;

public interface UserService {

	public User save(User user);

	public List<User> findAll();

	public User findByEmail(String email);

	public User findById(long id);

	public void sendEmail(User user);
	
	public List<User> findByRole(long roleId);
}
