package edu.mum.ea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.domain.Comment;
import edu.mum.ea.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public void delete(long id) {
		Comment comment = commentRepository.findById(id).get();
		commentRepository.delete(comment);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Comment findById(long id) {
		return commentRepository.findById(id).get();
	}

}
