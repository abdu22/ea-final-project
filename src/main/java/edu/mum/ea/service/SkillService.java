package edu.mum.ea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.domain.Skill;
import edu.mum.ea.repository.SkillRepository;

@Service
@Transactional
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	public List<Skill> findAll() {
		return skillRepository.findAll();
	}
}
