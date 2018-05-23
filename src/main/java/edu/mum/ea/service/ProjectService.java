package edu.mum.ea.service;

import java.util.List;

import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.Task;
import edu.mum.ea.utils.SearchProjectParam;

public interface ProjectService {

	public Project save(Project project);

	public List<Project> findAll();

	public Project findById(long id);

	public void removeTask(Task task);

	public List<Project> search(SearchProjectParam params);

	public void delete(long id);
}
