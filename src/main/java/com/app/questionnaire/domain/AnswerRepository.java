package com.app.questionnaire.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	//@Query("SELECT s.answerId, s.answerText, s.question.questionId, s.question.questionText FROM Answer s")
	//Iterable<Answer> findallAnswers();
	
	@Query(value="SELECT new com.app.questionnaire.domain.AnswerDto(s.answerId, s.answerText, s.question.questionId) FROM Answer s")
	List<AnswerDto>fetchAll();
	
}
