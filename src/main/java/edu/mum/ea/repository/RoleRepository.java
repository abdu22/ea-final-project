package edu.mum.ea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.Role;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer>{
}
