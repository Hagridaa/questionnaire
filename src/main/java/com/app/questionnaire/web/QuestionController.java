package com.app.questionnaire.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.questionnaire.domain.QuestionRepository;

public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;
}
