package com.app.questionnaire;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepositoryTest {
	//This annotation allows Spring to resolve
			//and inject collaborating beans into your bean.
			@Autowired
			private QuestionRepository qRepository;
			
			@Autowired
			private UserRepository userRepository;
			
			@Test
			public void createNewQuestion() {
				User user1 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","pii@gmail.com");
				userRepository.save(user1);
				Questionnaire questionnaire1 = new Questionnaire(null, "testQuestionnaire", "description ", user1);
				Question question1 = new Question(null, "Onko maito hyvää?", new Questiontype(null,"text"),questionnaire1);
				qRepository.save(question1);
				System.out.println("tulosta kysymys "+ question1);
				//assertThat(question1.getQuestionText()).isNotNull();
				Optional<Question> foundedQuestion = qRepository.findById(question1.getQuestionId());
				System.out.println("Löydetty kysymys: " + foundedQuestion.toString());
				assertEquals("Onko maito hyvää?", foundedQuestion.get().getQuestionText());
			}
			
}
