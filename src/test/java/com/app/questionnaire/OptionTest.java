package com.app.questionnaire;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Option;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionTest {
	
	@Test
	public void createNewOption() {
		
		//Long optionId, String optionText, Questiontype questiontype
		Questiontype questionType1 = new Questiontype(null,"option");
		Option option1 = new Option(null, "valinta1",questionType1);
		Option option2 = new Option(null, "valinta2",questionType1);
		Option option3 = new Option(null, "valinta3",questionType1);
		List<Option> optionlist1 = List.of(option1,option2,option3);
		assertThat(optionlist1).isNotEmpty();
		assertThat(option1).isNotEqualTo(option2);
		
	}
	
	@Test
	public void createNewQuestionWithOptions () {
		
		//luodaan uusi käyttäjä
		//(String username, String passwordHass, String role)
		User user1 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","pii@gmail.com");
		
		//luodaan uusi kysely
		//Long questionnaireId, String questionnaireTitle, String questionnaireDescription,
		//List<Question> questions, User user
		List<Question> questionlist1 = new ArrayList<>();
		
		Questionnaire questionnaire1 = new Questionnaire(null, "Tilojen viihtyisyys", "Tutkitaan tilojen viihtyisyyttä",questionlist1,user1);
		List<Questionnaire> questionnaires = List.of(questionnaire1);
		
		//luodaan uusi kysymys
		//Long questionId, String questionText, Questiontype questiontype, Questionnaire questionnaire,
		Questiontype questionType2 = new Questiontype(null,"option");
		
		//Luodaan uusi option lista
		//Long optionId, String optionText, Questiontype questiontype
		Option option1 = new Option(null, "valinta1",questionType2);
		Option option2 = new Option(null, "valinta2",questionType2);
		List<Option> optionlist1 = List.of(option1,option2);
		
		questionType2.setOptions(optionlist1);
		
		Question question1 = new Question(null, "Ovatko tilat viihtyisät?", questionType2, questionnaire1);
		
		assertThat(question1.getQuestiontype().getOptions()).isEqualTo(optionlist1);
	
	}

}
