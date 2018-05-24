package edu.mum.ea.dao;

import java.util.List;

import edu.mum.ea.domain.Project;

public interface ProjectDao extends GenericDao<Project> {
	List<Project> findByDeveloper(long userId);
}
