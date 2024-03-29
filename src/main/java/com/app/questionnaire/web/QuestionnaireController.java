package com.app.questionnaire.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.QuestionnaireRepository;

@CrossOrigin
@Controller
public class QuestionnaireController {
		
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionnaireRepository questionnaireRepository;
		
		// JSON questionnaires
		@RequestMapping(value="/api/questionnaires", method = RequestMethod.GET)
			public @ResponseBody List<Questionnaire> getRestQuestionnaires() {
				return (List<Questionnaire>) questionnaireRepository.findAll();
		}

		// RESTful service to retrieve an existing questionnaire by id
	    @RequestMapping(value="/api/questionnaires/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Questionnaire> findQuestionnaireRest(@PathVariable("id") Long questionnaireId) {	
	    	return questionnaireRepository.findById(questionnaireId);
	    }
	    
		// RESTful service to retrieve an existing questionnaire by id
	 //   @RequestMapping(value="/questionnaires/{id}/questions", method = RequestMethod.GET)
	  //  public @ResponseBody Optional<Questionnaire> findQuestionnaireIdRest(@PathVariable("id") Long questionnaireId) {	
	    //	return questionnaireRepository.findById(questionnaireId);
	    //}
	    
		// Thymeleaf listing the questions
		@RequestMapping(value="/questionnaires/{id}", method = RequestMethod.GET)
		public String getQuestions(@PathVariable("id") Long questionnaireId, Model model) {
			List<Question> questions = (List<Question>) questionRepository.findAll();
				model.addAttribute("questionnaire", questionnaireRepository.findById(questionnaireId).get());
				List<Question> questions2 = new ArrayList<Question>();
					
				for (Question question: questions) {
					if (question.getQuestionnaire().getQuestionnaireId().equals(questionnaireId)) {
						questions2.add(question);
					}
				}
		
				model.addAttribute("questions2", questions2);
				return "questionlist";
		}

	    // Fetch all questionnaires from database
		@RequestMapping(value = "/questionnaires", method = RequestMethod.GET)
		public String getQuestionnaires(Model model) {
			List<Questionnaire> questionnaires = (List<Questionnaire>) questionnaireRepository.findAll();
			model.addAttribute("questionnaires", questionnaires);
			return "questionnairelist";
		}
		
		// Thymeleaf form to add questionnaire 
	    @RequestMapping(value ="/newquestionnaire", method = RequestMethod.GET)
		public String getNewQuestionnaireForm(Model model) {
			model.addAttribute("questionnaire", new Questionnaire());
			return "addquestionnaireform";
		}
		
		// Thymeleaf save a new questionnaire
	    @RequestMapping(value = "/newquestionnaire", method = RequestMethod.POST)
	    public String saveQuestionnaire(@ModelAttribute Questionnaire questionnaire) {
		questionnaireRepository.save(questionnaire);
		return "redirect:/questionnaires";
		}

}
