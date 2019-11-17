package com.app.questionnaire.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.QuestionnaireRepository;

public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	// JSON questions
	@RequestMapping(value="/api/questions", method = RequestMethod.GET)
	public @ResponseBody List<Question> getRestQuestions() {
			return (List<Question>) questionRepository.findAll();
	}
	
	// Thymeleaf listing the questions
	@RequestMapping(value="/questions", method = RequestMethod.GET)
	public String getQuestions(Model model) {
			List<Question> questions = (List<Question>) questionRepository.findAll();
			model.addAttribute("questions", questions);
			return "questionlist";
	}
    
    // THYMELEAF ADD
    @RequestMapping(value ="/newquestion", method = RequestMethod.GET)
	public String getNewQuestionForm(Model model) {
		model.addAttribute("question", new Question());
		return "addquestionform";
	}
    
    // Thymeleaf save a new question
    @RequestMapping(value = "/savequestion", method = RequestMethod.POST)
    public String saveQuestion(@ModelAttribute Question question) {
	questionRepository.save(question);
	return "redirect:/questionlist";
	}
}
