package edu.mum.ea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.domain.Task;
import edu.mum.ea.repository.TaskRepository;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task save(Task task) {
		return taskRepository.save(task);
	}

	public void delete(long id) {
		Task task = taskRepository.findById(id).get();
		taskRepository.delete(task);
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Task findById(long id) {
		return taskRepository.findById(id).get();
	}

}
