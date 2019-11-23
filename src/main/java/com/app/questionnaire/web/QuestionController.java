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

public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;
	
	// JSON questions
	@RequestMapping(value="/api/questions", method = RequestMethod.GET)
	public @ResponseBody List<Question> getRestQuestions() {
			return (List<Question>) questionRepository.findAll();
	}

    // Thymeleaf add
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
