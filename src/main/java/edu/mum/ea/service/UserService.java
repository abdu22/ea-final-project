package edu.mum.ea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.domain.User;
import edu.mum.ea.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findByEmail(String email) {
		List<User> users = userRepository.findByEmailAllIgnoreCase(email);
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
}
