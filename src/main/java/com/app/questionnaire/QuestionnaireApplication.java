package com.app.questionnaire;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.questionnaire.domain.Answer;
import com.app.questionnaire.domain.AnswerRepository;
import com.app.questionnaire.domain.Question;
import com.app.questionnaire.domain.QuestionRepository;
import com.app.questionnaire.domain.Questionnaire;
import com.app.questionnaire.domain.QuestionnaireRepository;
import com.app.questionnaire.domain.Questiontype;
import com.app.questionnaire.domain.QuestiontypeRepository;
import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class QuestionnaireApplication {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionnaireApplication.class);  //uusi loggeriattribuutti

	public static void main(String[] args) {
		SpringApplication.run(QuestionnaireApplication.class, args);
	}
	
//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner Demo(QuestiontypeRepository questionTypeRepository, UserRepository userRepository, QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswerRepository aRepository) { 
		return (args) -> {
			log.info("save a couple of users,questionnaires for users and questions for questionnaires");
			
			//luodaan uusi käyttäjä
			//(String username, String passwordHass, String role)
			User user1 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","pii@gmail.com");
			User user2 = new User(null, "admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN","moo@gmail.com");
			userRepository.save(user1);
			userRepository.save(user2);
			log.info("Test users: " + user1 + " and " + user2);
			
			//luodaan uusi kysely
			//Long questionnaireId, String questionnaireTitle, String questionnaireDescription,
			//List<Question> questions, User user
			List<Question> questionlist1 = new ArrayList<>();
			List<Question> questionlist2 = new ArrayList<>();
			
			Questionnaire questionnaire1 = new Questionnaire(null, "Tilojen viihtyisyys", "Tutkitaan tilojen viihtyisyyttä",questionlist1,user1);
			Questionnaire questionnaire2 = new Questionnaire(null, "Tilojen moderniutta", "Tutkitaan tilojen moderniutta",questionlist2,user2);
			questionnaireRepository.save(questionnaire1);
			questionnaireRepository.save(questionnaire2);
			List<Questionnaire> questionnaires = List.of(questionnaire1,questionnaire2);
			log.info("Test questionnaires are: " + questionnaire1, questionnaire2);
			log.info("Testlist is: "+ questionnaires);
			
			//luodaan uusi kysymys
			//Long questionId, String questionText, Questiontype questiontype, Questionnaire questionnaire,
			//List<Answer> answers
			Questiontype questionType1 = new Questiontype(null,"text");
			questionTypeRepository.save(questionType1);
			
			Question question1 = new Question(null, "Ovatko tilat viihtyisät?", questionType1, questionnaire1);
			Question question2 = new Question(null,"Kuinka viihtyisät tilat koulussamme mielestäsi on?", questionType1, questionnaire1);
			questionRepository.save(question1);
			questionRepository.save(question2);
			
			//questionlist1.add(question1);

			
			log.info("Test questions are: " + question1 + " and " + question2);
			log.info("Test questionlist is: " + questionlist1);
			
//			Answer answer1 = new Answer(null,"Minusta tilat ovat modernit",question1);
//			aRepository.save(answer1);
//			log.info("First test answer is: " + answer1);
//			
//			Answer answer2 = new Answer(null,"Minusta tilat ovat viihtyisät",question2);
//			aRepository.save(answer2);
//			log.info("Second test answer is: " + answer2);
//			
//			List<Answer> answers = List.of(answer1,answer2);
//			log.info("Test answerlist is: "+ answers);
						

			//create object mapper
			ObjectMapper objectMapper = new ObjectMapper();

			//Answer(Long answerId, String answerText, Question question
			Answer answer3 = new Answer(null,"tilat ovat viihtyisät",question1);
			Answer answer4 = new Answer(null,"tilat ovat modernit",question2);
			
			log.info("Answer1 is: " + answer4);

			question1.setAnswers(List.of(answer3));
			question2.setAnswers(List.of(answer4));
			 //configure objectMapper for pretty input
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

	        //write answerObj object to answer1.json file
	        
	        objectMapper.writeValue(new File("answer1.json"), List.of(answer3, answer4));
	        
		};
	}
}