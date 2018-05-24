package edu.mum.ea.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.config.RabbitMqConfig;
import edu.mum.ea.dao.UserDao;
import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.User;
import edu.mum.ea.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void save(User user) {
		userDao.save(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findByEmail(String email) {
		List<User> users = userDao.findByEmailAllIgnoreCase(email);
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}	
	

	public User findById(long id) {
		return userDao.findOne(id);
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
		List<User> users = userDao.findByRole(roleId);
		if (users.size() > 0) {
			return users;
		}
		return null;		
	}
	
	public void delete(long id) {
		userDao.delete(id);
	}
	
	public List<User> findAvailableDevelopers(Project project) {
		List<User> availableDevs = userDao.findAvailableDevelopers(project.getId());
		
		return availableDevs;
	}
}
