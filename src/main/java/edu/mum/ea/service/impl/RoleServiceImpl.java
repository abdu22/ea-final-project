package edu.mum.ea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.dao.RoleDao;
import edu.mum.ea.domain.Role;
import edu.mum.ea.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> getAll() {
		return roleDao.findAll();
	}

	public Role getRole(long id) {
		return roleDao.findOne(id);
	}
}
