package edu.mum.ea.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import edu.mum.ea.domain.Project;
import edu.mum.ea.domain.ProjectStatusEnum;
import edu.mum.ea.repository.ProjectSearchRepository;
import edu.mum.ea.utils.SearchProjectParam;

@Repository
public class ProjectSearchRepositoryImpl implements ProjectSearchRepository {
	
	@Autowired
	private EntityManager em;

	public List<Project> search(SearchProjectParam params) {
		String sql = "select distinct p from Project p where 1 = 1";

		if (!StringUtils.isEmpty(params.getName())) {
			sql += " and LOWER(p.name) like CONCAT('%', LOWER(:name), '%')";
		}

		if (!StringUtils.isEmpty(params.getStatus()) && !"ALL".equals(params.getStatus())) {
			sql += " and p.status = :status";
		}

		if (!StringUtils.isEmpty(params.getLocation())) {
			sql += " and LOWER(p.location) like CONCAT('%', LOWER(:location), '%')";
		}

		TypedQuery<Project> query = em.createQuery(sql, Project.class);

		if (!StringUtils.isEmpty(params.getName())) {
			query.setParameter("name", params.getName());
		}
		if (!StringUtils.isEmpty(params.getStatus()) && !"ALL".equals(params.getStatus())) {
			query.setParameter("status", ProjectStatusEnum.valueOf(params.getStatus()));
		}
		if (!StringUtils.isEmpty(params.getLocation())) {
			query.setParameter("location", params.getLocation());
		}
		return query.getResultList();
	}
}
