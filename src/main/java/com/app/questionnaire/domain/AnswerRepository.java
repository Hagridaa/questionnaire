package com.app.questionnaire.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	@Query("SELECT s.answerId, s.answerText FROM Answer s")
	Iterable<Answer> findallAnswers();
	
}
