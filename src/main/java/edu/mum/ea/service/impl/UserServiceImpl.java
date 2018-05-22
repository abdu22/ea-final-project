package edu.mum.ea.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.config.RabbitMqConfig;
import edu.mum.ea.domain.User;
import edu.mum.ea.repository.UserRepository;
import edu.mum.ea.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

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
	

	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	public void sendEmail(User user) {
		Map<String, String> map = new HashMap<>();
		map.put("email_to", user.getEmail());

		map.put("email_title", "Welcome");
		map.put("email_content", "Hello " + user.getFirstName() + "!");

		rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_QUEUE, map);
	}

	@Override
	public List<User> findByRole(long roleId) {
		List<User> users = userRepository.findByRole(roleId);
		if (users.size() > 0) {
			return users;
		}
		return null;		
	}
}
