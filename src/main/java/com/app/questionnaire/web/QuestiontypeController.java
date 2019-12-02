package com.app.questionnaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.QuestiontypeRepository;

@Controller
public class QuestiontypeController {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestiontypeRepository questiontypeRepository;
	
	
	// Select questiontype page
	// Puuttuu vielä tietyn kyselyn id
	
	@RequestMapping(value = "/newmcquestion/{id}", method = RequestMethod.GET)
	public String getQuestiontype(Model model) {
		// model.addAttribute("questiontype", new Book()); // "tyhjä" kirjaolio
		model.addAttribute("questiontypes", questiontypeRepository.findAll());
		return "questiontypeselection";
	}
	
	
    
}
