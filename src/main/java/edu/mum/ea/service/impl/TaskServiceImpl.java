package edu.mum.ea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.dao.TaskDao;
import edu.mum.ea.domain.Task;
import edu.mum.ea.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	public void save(Task task) {
		taskDao.save(task);
	}

	public void delete(long id) {
		taskDao.delete(id);
	}

	public List<Task> findAll() {
		return taskDao.findAll();
	}

	public Task findById(long id) {
		return taskDao.findOne(id);
	}

}
