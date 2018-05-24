package edu.mum.ea.service;

import java.util.List;

import edu.mum.ea.domain.Task;

public interface TaskService {

	public void save(Task task);

	public void delete(long id);

	public List<Task> findAll();

	public Task findById(long id);

}
