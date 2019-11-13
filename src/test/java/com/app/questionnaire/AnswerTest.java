package com.app.questionnaire;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Answer;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerTest {
	
	@Test
	public void createNewAnswer() {
		User user6 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "mimmi@gmail.com");
		Questionnaire questionnaire6 = new Questionnaire(null, "testQuestionnaire", "description ", user6);
		Question question = new Question(null, "Onko maito hyvää?", new Questiontype(null,"text"),questionnaire6);
		System.out.println("tulosta testi kysymys1: " + question.toString());
		//Long answerId, String answerText, Question question
		Answer answer = new Answer(null,"Minusta maito on hyvää",question);
		System.out.println("Vastaus on: " + answer);
		assertThat(answer.getAnswerText()).isNotEmpty();
		}
}
