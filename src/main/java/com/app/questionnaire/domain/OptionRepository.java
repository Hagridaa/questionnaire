package com.app.questionnaire.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository <Option, Long>{
	Optional<Option> findByOptionId(Long Id);
	
	List<Option> findAllByQuestion(Optional<Question> question);
}
