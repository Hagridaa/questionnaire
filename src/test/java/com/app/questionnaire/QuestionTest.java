package com.app.questionnaire;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTest {

	@Test
	public void createNewQuestion_questionShouldNotBeEmpty() {
		User user4 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "piippo@gmail.com");
		Questionnaire questionnaire1 = new Questionnaire(null, "testQuestionnaire", "description ", user4);
		Question question = new Question(null, "Onko maito hyvää?", new Questiontype(null,"text"),questionnaire1);
		System.out.println("tulosta testi kysymys1: " + question.toString());
		assertThat(question.getQuestionText()).isNotEmpty();
		}
	
	@Test
	public void createNewQuestion_questionShouldBeCorrect() {
		User user4 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "piippo@gmail.com");
		Questionnaire questionnaire1 = new Questionnaire(null, "testQuestionnaire", "description ", user4);
		Question question = new Question(null, "Onko maito hyvää?", new Questiontype(null,"text"),questionnaire1);
		System.out.println("tulosta testi kysymys2: " + question.toString());
		assertEquals("Onko maito hyvää?", question.getQuestionText());
		}
}
