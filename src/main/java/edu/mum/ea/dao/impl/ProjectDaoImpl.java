package edu.mum.ea.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.mum.ea.dao.ProjectDao;
import edu.mum.ea.domain.Project;

@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project> implements ProjectDao {

	public ProjectDaoImpl() {
		super.setDaoType(Project.class);
	}

	public List<Project> findByDeveloper(long userId) {
		TypedQuery<Project> query = entityManager.createQuery("select distinct p from Project p join p.users u where u.id = :userId", Project.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}