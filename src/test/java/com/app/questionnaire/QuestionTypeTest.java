package com.app.questionnaire;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.User;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTypeTest {
	
	@Test
	public void createNewQuestionType() {
		Question question1 = new Question();
		question1.setQuestionId((long) 1);
		question1.setQuestionText("Testi");
		
		Questiontype questionType1 = new Questiontype((long) 1, "option");
		questionType1.setQuestions(List.of(question1));
		question1.setQuestiontype(questionType1);
		
	}

}
