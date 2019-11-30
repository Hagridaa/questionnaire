package com.app.questionnaire.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface QuestiontypeRepository extends CrudRepository <Questiontype, Long>{
	// Optional<Movie> findByName(String name);
	List<Questiontype> findByQuestiontypeName(String questiontypeName);
}
