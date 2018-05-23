package edu.mum.ea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.Task;
import edu.mum.ea.repository.ProjectRepository;
import edu.mum.ea.repository.ProjectSearchRepository;
import edu.mum.ea.repository.TaskRepository;
import edu.mum.ea.service.ProjectService;
import edu.mum.ea.utils.SearchProjectParam;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectSearchRepository projectSearchRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	public Project save(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}
	
	public Project findById(long id) {
		return projectRepository.findById(id).get();
	}
	
	public void removeTask(Task task) {
		taskRepository.delete(task);
	}
	
	public List<Project> search(SearchProjectParam params) {
		return projectSearchRepository.search(params);
	}
	
	public void delete(long id) {
		Project project = projectRepository.findById(id).get();
		projectRepository.delete(project);
	}
}
