package edu.mum.ea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
