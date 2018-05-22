package edu.mum.ea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByEmailAllIgnoreCase(String email);

	@Query("select distinct u from User u join u.roles s where s.id = :roleId")
	List<User> findByRole(@Param("roleId") long roleId);
}
