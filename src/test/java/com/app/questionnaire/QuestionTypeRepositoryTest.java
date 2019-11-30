package com.app.questionnaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.QuestiontypeRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class QuestionTypeRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(QuestionTypeRepositoryTest.class);
	@Autowired
	QuestiontypeRepository questiontypeRepository;
	
	@Test
	public void createNewQuestionType_findItByName() {
		Questiontype questionType2 = new Questiontype(null,"option");
		questiontypeRepository.save(questionType2);
    	List<Questiontype> questionTypes = questiontypeRepository.findByQuestiontypeName("option");
    	for (int i = 0; i < questionTypes.size(); i++) {
    		if (questionTypes.get(i).getQuestiontypeName().equals("option")) {
    		Questiontype foundQuestionType = questionTypes.get(i);
    		assertEquals("option", foundQuestionType.getQuestiontypeName());
    		}
    	}
	}
	
	@Test
	public void createNewQuestionType_toString() {
		Questiontype questionType2 = new Questiontype(null,"option2");
		questiontypeRepository.save(questionType2);
    	List<Questiontype> questionTypes = questiontypeRepository.findByQuestiontypeName("option2");
    	for (int i = 0; i < questionTypes.size(); i++) {
    		if (questionTypes.get(i).getQuestiontypeName().equals("option2")) {
    		Questiontype foundQuestionType = questionTypes.get(i);
    		assertEquals(questionType2.toString(), foundQuestionType.toString());
    		}
    	}
	}
}
