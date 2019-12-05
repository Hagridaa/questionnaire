package com.app.questionnaire.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.questionnaire.domain.OptionRepository;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.QuestionnaireRepository;
import com.app.questionnaire.domain.QuestiontypeRepository;


@Controller
public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	@Autowired
	QuestiontypeRepository questiontypeRepository;
	
	@Autowired
	OptionRepository optionRepository;
	
	// JSON questions
	@RequestMapping(value="/api/questions", method = RequestMethod.GET)
	public @ResponseBody List<Question> getRestQuestions() {
			return (List<Question>) questionRepository.findAll();
	}
	
	// get JSON question by id
	@RequestMapping(value="/api/questions/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Question> findQuestionById(@PathVariable("id") Long questionId) {
		return questionRepository.findById(questionId);
	}

    // Thymeleaf add text question
    @RequestMapping(value ="/newquestion/{id}", method = RequestMethod.GET) // lisää kysymyksen tietyn id:n mukaan
	public String getNewQuestionForm(@PathVariable("id") Long questionnaireId, Model model) {
		model.addAttribute("question", new Question());
		model.addAttribute("questionnaireId", questionnaireId);
		return "addquestionform";
	}
    
    // Thymeleaf save a new text question
    @RequestMapping(value = "/savequestion/{id}", method = RequestMethod.POST) // tallentaa kysymyksen tietyn id:n mukaan
    public String saveQuestion(@PathVariable("id") Long questionnaireId, @ModelAttribute Question question) {
    question.setQuestionnaire(questionnaireRepository.findById(questionnaireId).get()); // haetaan tietyn kyselyn id
	questionRepository.save(question);
	return "redirect:/questionnaires/" + questionnaireId; // uudelleenohjaa tiettyyn kyselyyn id:n mukaan
	
	}
    
}