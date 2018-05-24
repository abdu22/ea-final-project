package edu.mum.ea.dao;

import java.util.List;

import edu.mum.ea.domain.User;

public interface UserDao extends GenericDao<User> {

	List<User> findByEmailAllIgnoreCase(String email);

	List<User> findByRole(long roleId);

	List<User> findAvailableDevelopers(long projectId);
}
