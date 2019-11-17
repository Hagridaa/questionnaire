package com.app.questionnaire;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
//annotation to use for regular
//tests, as well as some specialized variants for testing parts of your application
public class UserTest {

	@Test
	public void createNewUser() {	
		//Long id, String username, String passwordHass, String role, String email
		User user3 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "pii@gmail.com");
		System.out.println("tulosta testi user3: " + user3.toString());
		assertThat(user3.getUsername()).isEqualTo("user");
		//assertThat(user3.getUsername().endsWith("r"));
		//assertThat(user3.getUsername().isEmpty());
	}
	
	 @Test
	public void newUser_shouldHaveQuestionnares() {
		User user4 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "piippo@gmail.com");
		Questionnaire questionnaire1 = new Questionnaire(null, "testQuestionnaire", "description ", user4);
		Questionnaire quesitonnaire2 = new Questionnaire(null, "testQuestionnaire2", "description ", user4);
		List<Questionnaire> questionnaires = List.of(questionnaire1, quesitonnaire2);
		user4.setQuestionnares(questionnaires);
		assertThat(user4.getQuestionnaires()).size().isNotNull();
	} 
		
	}


