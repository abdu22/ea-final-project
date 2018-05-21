package edu.mum.ea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ea.domain.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
