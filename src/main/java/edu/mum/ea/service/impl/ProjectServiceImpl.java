package edu.mum.ea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.dao.ProjectDao;
import edu.mum.ea.dao.ProjectSearchDao;
import edu.mum.ea.dao.TaskDao;
import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.Task;
import edu.mum.ea.service.ProjectService;
import edu.mum.ea.utils.SearchProjectParam;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProjectSearchDao projectSearchDao;
	
	@Autowired
	private TaskDao taskDao;

	public void save(Project project) {
		projectDao.save(project);
	}

	public List<Project> findAll() {
		return projectDao.findAll();
	}
	
	public Project findById(long id) {
		return projectDao.findOne(id);
	}
	
	public void removeTask(Task task) {
		taskDao.delete(task.getId());
	}
	
	public List<Project> search(SearchProjectParam params) {
		return projectSearchDao.search(params);
	}
	
	public void delete(long id) {
		projectDao.delete(id);
	}

	@Override
	public List<Project> findByDeveloper(long userId) {
		return projectDao.findByDeveloper(userId);
	}
}
