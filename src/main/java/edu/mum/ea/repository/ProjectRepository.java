package edu.mum.ea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query("select distinct p from Project p join p.users u where u.id = :userId")
	List<Project> findByDeveloper(@Param("userId") long userId);

}
