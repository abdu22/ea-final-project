package edu.mum.ea.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.mum.ea.dao.UserDao;
import edu.mum.ea.domain.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super.setDaoType(User.class);
	}
	
	public List<User> findByEmailAllIgnoreCase(String email) {
		TypedQuery<User> query = entityManager.createQuery(
				"select u from User u where lower(u.email) like lower(concat('%', :email,'%'))", User.class);
		query.setParameter("email", email);
		return query.getResultList();	
	}

	public List<User> findByRole(long roleId) {
		TypedQuery<User> query = entityManager.createQuery("select distinct u from User u join u.roles s where s.id = :roleId", User.class);
		query.setParameter("roleId", roleId);
		return query.getResultList();		
	}
	
	public List<User> findAvailableDevelopers(long projectId) {
		TypedQuery<User> query = entityManager.createQuery("select distinct u from User u, Project p, Role r "
				+ "where p.id = :projectId and not (u member of p.users) and r.id = 2 and (r member of u.roles)", User.class);
		query.setParameter("projectId", projectId);
		return query.getResultList();	
	}
}