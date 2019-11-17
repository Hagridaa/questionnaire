package com.app.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.QuestionnaireRepository;
import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;

@SpringBootApplication
public class QuestionnaireApplication {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionnaireApplication.class);  //uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(QuestionnaireApplication.class, args);
	}
	
//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner Demo(UserRepository userRepository, QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository) { 
		return (args) -> {
			log.info("save a couple of users");
			
			Question question1 = new Question("Kuinka viihtyisät tilat koulussamme mielestäsi on?", null);
			Question question2 = new Question("Kuinka hyvä kurssitajonta mielestäsi on asteikolla 1-5?", null);
			questionRepository.save(question1);
			questionRepository.save(question2);
			
			
			List<Question> questionlist = new ArrayList<>();
			questionlist.add(question1);
			questionlist.add(question2);
			
			// Tämä lista on tyhjä
			List<Question>questionlist2 = new ArrayList<>();
			
			Questionnaire questionnaire1 = new Questionnaire("Koulukysely1", "Koulun viihtyvyyden kartoitus", null, null);
			Questionnaire questionnaire2 = new Questionnaire("Koulukysely2", "Kurssitarjonnan kartoitus", null, null);
			questionnaireRepository.save(questionnaire1);
			questionnaireRepository.save(questionnaire2);
			
		
			//(String username, String passwordHass, String role)
			User user1 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","pii@gmail.com");
			User user2 = new User(null, "admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN","moo@gmail.com");
			userRepository.save(user1);
			userRepository.save(user2);

};
	}
}
