package edu.mum.ea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.domain.Task;
import edu.mum.ea.domain.Project;
import edu.mum.ea.repository.TaskRepository;
import edu.mum.ea.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
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
}
