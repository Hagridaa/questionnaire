package com.app.questionnaire;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Answer;
import com.app.questionnaire.domain.AnswerRepository;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnswerRepositoryTest {
	
	@Autowired
	private AnswerRepository aRepository;
	
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private QuestionRepository qRepository;
	
	@Test
	public void createNewAnswer() {
		
		User user7 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","pm@gmail.com");
		uRepository.save(user7);
		
		Questionnaire questionnaire7 = new Questionnaire(null, "testQuestionnaire", "description ", user7);
		Question question2 = new Question(null, "Onko suklaa hyvää?", new Questiontype(null,"text"),questionnaire7);
		qRepository.save(question2);
		System.out.println("tulosta kysymys2 "+ question2);
		
		Answer answer = new Answer(null,"Minusta suklaa on hyvää",question2);
		System.out.println("Vastaus1 on: " + answer);
		
		aRepository.save(answer);
		
		Optional<Answer> foundedAnswer1 = aRepository.findById(answer.getAnswerId());
		System.out.println("Löydetty vastaus1 on: " + foundedAnswer1.toString());

		assertEquals("Minusta suklaa on hyvää", foundedAnswer1.get().getAnswerText());
		
	}

}
