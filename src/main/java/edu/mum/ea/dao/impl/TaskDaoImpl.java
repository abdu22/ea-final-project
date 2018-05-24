package edu.mum.ea.dao.impl;

import org.springframework.stereotype.Repository;

import edu.mum.ea.dao.TaskDao;
import edu.mum.ea.domain.Task;

@Repository
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {

	public TaskDaoImpl() {
		super.setDaoType(Task.class);
	}
}