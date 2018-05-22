package edu.mum.ea.service;

import java.util.List;

import edu.mum.ea.domain.Role;

public interface RoleService {
	public List<Role> getAll();

	public Role getRole(long id);
}
