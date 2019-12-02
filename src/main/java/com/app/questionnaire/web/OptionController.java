package com.app.questionnaire.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.questionnaire.domain.Option;
import com.app.questionnaire.domain.OptionRepository;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.QuestionnaireRepository;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.QuestiontypeRepository;

import net.bytebuddy.asm.Advice.This;

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
	    /* 
	    
	    @RequestMapping(value = "/replace", method = RequestMethod.GET)
	    public String replace(@RequestParam("id") Long optionId, Model model) {
	        Option option = optionRepository.findById(optionId);
	        model.addAttribute("entities", Arrays.asList(entity)); // sending the row as list, so that the loop can iterate and will produce a single row
	        return "file :: entity-row";
	    }
	    */
	    
	 // Thymeleaf save a new multiple choice question
	    @RequestMapping(value = "/saveoptionquestion/{id}", method = RequestMethod.POST) // tallentaa kysymyksen tietyn id:n mukaan
	    public String saveOptionQuestion(@PathVariable("id") Long questionnaireId, @ModelAttribute Question question) {
	    question.setQuestionnaire(questionnaireRepository.findById(questionnaireId).get()); // haetaan tietyn kyselyn id
		questionRepository.save(question);
		return "redirect:/newoptionquestion/" + questionnaireId; // uudelleenohjaa tiettyyn kyselyyn id:n mukaan
		
		}
	    /*
	    @RequestMapping(value="/newoptionquestion/{id}", method = RequestMethod.GET)
		public String getQuestions(@PathVariable("id") Long questionId, Model model) {
				model.addAttribute("question", questionRepository.findById(questionId).get());
				return "addoptionquestion";
		}*/
		
	/*	
	 * // RESTful service to retrieve an existing questionnaire by id
	    @RequestMapping(value="/api/questionnaires/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Questionnaire> findQuestionnaireRest(@PathVariable("id") Long questionnaireId) {	
	    	return questionnaireRepository.findById(questionnaireId);
	    }
	 * //add option
		@RequestMapping(value = "/add", params = {"addOption"})
		public String addOption(Question question, BindingResult result) {
			log.info("Question: " + question.toString());
		    question.getQuestiontype().getOptions().add(new Option());
		    return "addoptionquestion";
		    }
		

	    // Thymeleaf add a new optionquestion
	    @RequestMapping(value ="/newoptionquestion/{id}", method = RequestMethod.GET) // lisää optionkysymyksen tietyn id:n mukaan
		public String getNewOptionQuestionForm(@PathVariable("id") Long questionnaireId, Model model) {
	    	Question newQuestion = new Question();
	    	List<Questiontype> questionTypes = questiontypeRepository.findByQuestiontypeName("option");
	    	for (int i = 0; i < questionTypes.size(); i++) {
	    	log.info("Founded questionType: " + questionTypes.get(i));
	    		if (questionTypes.get(i).getQuestiontypeName().equals("option")) {
	    		Questiontype foundQuestionType = questionTypes.get(i);
//	    		log.info("Found questiontype from Database: " + foundQuestionType.toString());
	    		newQuestion.setQuestiontype(foundQuestionType);
	    		}
	    	}
	    	
	    	model.addAttribute("question", newQuestion);
			model.addAttribute("questionnaireId", questionnaireId);
			return "addoptionquestion";
		}
	    
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String save(Question question, BindingResult result) {
	    questionRepository.save(question);
	    return "redirect:/addoptionquestion";
	    } */
	    
	    // Thymeleaf save a new optionquestion
//	    @RequestMapping(value = "/saveoptionquestion/{id}", method = RequestMethod.POST) // tallentaa optionkysymyksen tietyn id:n mukaan
//	    public String saveOptionQuestion(@PathVariable("id") Long questionnaireId, @ModelAttribute Option option) {
//	    option.setQuestionnaire(questionnaireRepository.findById(questionnaireId).get()); // haetaan tietyn kyselyn id
//		questionRepository.saveAll((Iterable<S>) option);
//		return "redirect:/questionnaires/" + questionnaireId; // uudelleenohjaa tiettyyn kyselyyn id:n mukaan
//		
//		}
	    
		
		
		
	    // JSON options
		@RequestMapping(value="/api/options", method = RequestMethod.GET)
		public @ResponseBody List<Option> getRestQuestions() {
				return (List<Option>) optionRepository.findAll();
		}
		
		// JSON get options by question id
	 	@RequestMapping(value="/api/questions/{id}/options", method = RequestMethod.GET)
		public @ResponseBody List<Option> findAllOptionsByQuestionId(@PathVariable("id") Long questionId) {
			Optional<Question> question = questionRepository.findById(questionId);
			return optionRepository.findAllByQuestion(question);
		}
	    
	}

