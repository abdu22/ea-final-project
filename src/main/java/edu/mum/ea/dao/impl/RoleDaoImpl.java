package edu.mum.ea.dao.impl;

import org.springframework.stereotype.Repository;

import edu.mum.ea.dao.RoleDao;
import edu.mum.ea.domain.Role;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super.setDaoType(Role.class);
	}
}