package com.app.questionnaire.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.QuestionnaireRepository;

@Controller
public class QuestionnaireController {
		
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
		// RESTful service to get all questionnaires
		@RequestMapping("/questionnaires")
		public Iterable<Questionnaire> getQuestionnaires() {
			return questionnaireRepository.findAll();
		}

		// RESTful service to get a questionnaire by id
	    @RequestMapping(value="/api/questionnaires/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Questionnaire> findQuestionnaireRest(@PathVariable("id") Long questionnaireId) {	
	    	return questionnaireRepository.findById(questionnaireId);
	    }
	    
	    // Fetch all questionnaires from database
		@RequestMapping(value = "/questionnairelist", method = RequestMethod.GET)
		public String getQuestionnaires(Model model) {
			List<Questionnaire> questionnaires = (List<Questionnaire>) questionnaireRepository.findAll();
			model.addAttribute("questionnaires", questionnaires);
			return "questionnairelist";
		}

}
