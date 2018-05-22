package edu.mum.ea.service;

import java.util.List;

import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.Task;

public interface ProjectService {

	public Project save(Project project);

	public List<Project> findAll();
	
	public Project findById(long id);
	
	public void removeTask(Task task);
}
