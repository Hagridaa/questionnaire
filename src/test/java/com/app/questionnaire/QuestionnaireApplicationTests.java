package com.app.questionnaire;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionnaireApplicationTests {
	
	@Autowired
	private UserController userController;

	@Test
	public void contextLoads()  throws Exception {
		assertThat(userController).isNotNull();
	}

}
