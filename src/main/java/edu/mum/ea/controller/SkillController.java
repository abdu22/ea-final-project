package edu.mum.ea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.ea.domain.Skill;
import edu.mum.ea.service.SkillService;

@Controller
@RequestMapping("/admin/skill")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("skills", skillService.findAll());
		return "admin/skill/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String update(@ModelAttribute("skill") Skill skill) {
		return "admin/skill/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute("skill") Skill skill) {
		skillService.save(skill);
		return "redirect:/admin/skill/";
	}
}
