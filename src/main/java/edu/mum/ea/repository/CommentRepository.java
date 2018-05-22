package edu.mum.ea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
