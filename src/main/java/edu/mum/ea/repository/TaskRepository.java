package edu.mum.ea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
