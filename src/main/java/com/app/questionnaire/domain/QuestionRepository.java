package com.app.questionnaire.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface QuestionRepository extends CrudRepository<Question, Long> {

//	Optional<Question> findByname(String string);
	

}
