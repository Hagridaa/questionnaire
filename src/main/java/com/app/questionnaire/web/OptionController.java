package com.app.questionnaire.web;

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

import com.app.questionnaire.domain.Option;
import com.app.questionnaire.domain.OptionRepository;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.QuestionnaireRepository;
import com.app.questionnaire.domain.Questiontype;
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
		
		//add option
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
	    }
	    
	    // Thymeleaf save a new optionquestion
//	    @RequestMapping(value = "/saveoptionquestion/{id}", method = RequestMethod.POST) // tallentaa optionkysymyksen tietyn id:n mukaan
//	    public String saveOptionQuestion(@PathVariable("id") Long questionnaireId, @ModelAttribute Option option) {
//	    option.setQuestionnaire(questionnaireRepository.findById(questionnaireId).get()); // haetaan tietyn kyselyn id
//		questionRepository.saveAll((Iterable<S>) option);
//		return "redirect:/questionnaires/" + questionnaireId; // uudelleenohjaa tiettyyn kyselyyn id:n mukaan
//		
//		}
	    
	}

