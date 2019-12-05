package com.app.questionnaire.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.questionnaire.domain.Option;
import com.app.questionnaire.domain.OptionRepository;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.QuestionnaireRepository;
import com.app.questionnaire.domain.QuestiontypeRepository;


@Controller
public class OptionController {

	private static final Logger log = LoggerFactory.getLogger(OptionController.class);
		@Autowired
		QuestionRepository questionRepository;
		
		@Autowired
		QuestionnaireRepository questionnaireRepository;
		
		@Autowired
		QuestiontypeRepository questiontypeRepository;
		
		@Autowired
		OptionRepository optionRepository;
		
		// Thymeleaf add multiple choice question
	    @RequestMapping(value ="/newoptionquestion/{id}", method = RequestMethod.GET) // lisää kysymyksen tietyn id:n mukaan
		public String getNewMCQuestionForm(@PathVariable("id") Long questionnaireId, Model model) {
	    	Option option = new Option();
	    	model.addAttribute("questiontypes", questiontypeRepository.findAll());
	    	model.addAttribute("question", new Question());
	    	model.addAttribute("questionnaireId", questionnaireId);
	    	model.addAttribute("option", option);
	    	model.addAttribute("options", optionRepository.findAll());
	    	model.addAttribute("option.question", option.getQuestion());
			return "addoptionquestion";
		}
	   
	    
	    // Thymeleaf save a new multiple choice question
	    @RequestMapping(value = "/saveoptionquestion/{id}", method = RequestMethod.POST) // tallentaa kysymyksen tietyn id:n mukaan
	    public String saveOptionQuestion(@PathVariable("id") Long questionnaireId, @ModelAttribute Question question) {
	    question.setQuestionnaire(questionnaireRepository.findById(questionnaireId).get()); // haetaan tietyn kyselyn id
		questionRepository.save(question);
		return "redirect:/newoptionquestion/" + questionnaireId; // uudelleenohjaa tiettyyn kyselyyn id:n mukaan
		
		}
	   
		
	    // JSON options
		@RequestMapping(value="/api/options", method = RequestMethod.GET)
		public @ResponseBody List<Option> getRestQuestions() {
				return (List<Option>) optionRepository.findAll();
		}
		
		// get JSON option by id
		@RequestMapping(value="/api/options/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Option> findOptionById(@PathVariable("id") Long optionId) {
			return optionRepository.findById(optionId);
		}
		
		// JSON get options by question id
	 	@RequestMapping(value="/api/questions/{id}/options", method = RequestMethod.GET)
		public @ResponseBody List<Option> findAllOptionsByQuestionId(@PathVariable("id") Long questionId) {
			Optional<Question> question = questionRepository.findById(questionId);
			return optionRepository.findAllByQuestion(question);
		}
	 	
	 	
	 /*	@RequestMapping( value = "/saveoption", method = RequestMethod.POST )
	    public Option save(@RequestBody Option option){
	        return optionRepository.save(option);
	    }
	  */
	 	
	 	// Thymeleaf add new option
	    @RequestMapping(value ="/newoption/{id}", method = RequestMethod.GET) // lisää kysymyksen tietyn id:n mukaan
		public String getNewOption(@PathVariable("id") Long questionId, Model model) {
			model.addAttribute("option", new Option());
			model.addAttribute("questionId", questionId);
			return "addoptionquestion";
		}
	 	
	 	// Thymeleaf save a new option
	    @RequestMapping(value = "/saveoption/{id}", method = RequestMethod.POST) // tallentaa kysymyksen tietyn id:n mukaan
	    public String saveOption(@PathVariable("id") Long questionId, @ModelAttribute Option option) {
	    option.setQuestion(questionRepository.findById(questionId).get()); // haetaan tietyn kyselyn id
		optionRepository.save(option);
		return "redirect:../questionnaires"; // uudelleenohjaa tiettyyn kyselyyn id:n mukaan
		
		}
	    
	}

