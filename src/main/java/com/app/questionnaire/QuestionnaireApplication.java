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
			
			Questionnaire questionnaire1 = new Questionnaire(null, "Tilojen viihtyisyys", "Tutkitaan tilojen xx",questionlist1,user1);
			Questionnaire questionnaire2 = new Questionnaire(null, "Tilojen viihtyisyys2", "Tutkitaan tilojen xx2",questionlist2,user2);
			questionnaireRepository.save(questionnaire1);
			questionnaireRepository.save(questionnaire2);
			//List<Questionnaire> questionnaires = List.of(questionnaire1,questionnaire2);
			//log.info("Test questionnaires are: " + questionnaire1, questionnaire2);
			//log.info("Testlist is: "+ questionnaires);
			
			//luodaan uusi kysymys
			//Long questionId, String questionText, Questiontype questiontype, Questionnaire questionnaire,
			//List<Answer> answers
			Questiontype questionType1 = new Questiontype(null,"text");
			questionTypeRepository.save(questionType1);
			Question question1 = new Question(null, "Onko maito hyvää?", questionType1, questionnaire1);
			Question question2 = new Question(null,"Kuinka viihtyisät tilat koulussamme mielestäsi on?", questionType1, questionnaire1);
			//Question question2 = new Question("Kuinka hyvä kurssitajonta mielestäsi on asteikolla 1-5?", null);
			questionRepository.save(question1);
			questionRepository.save(question2);

			
			log.info("Test questions are: " + question1 + " and " + question2);
			log.info("Test questionlist is: " + questionlist1);
			
			Answer answer1 = new Answer(null,"Minusta maito on hyvää",question1);
			aRepository.save(answer1);
			log.info("First test answer is: " + answer1);
			
			Answer answer2 = new Answer(null,"Minusta tilat ovat viihtyisät",question2);
			aRepository.save(answer2);
			log.info("Second test answer is: " + answer2);
			
			//List<Answer> answers = List.of(answer1,answer2);
			//log.info("Test answerlist is: "+ answers);
			
			
			
			
			
			
		
			
			
			//create a answer object for testing answer

			//create object mapper
//			 ObjectMapper objectMapper = new ObjectMapper();
//
//			User user3 = new User(1L, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "piippo@gmail.com");
//
//			Questionnaire questionnaire3 = new Questionnaire(1L, "testQuestionnaire", "description ", user3);
////			List <Questionnaire> questionnaires = List.of(questionnaire3);
////			user3.setQuestionnares(questionnaires);
//
//			Question question3 = new Question(1L, "Ovatko koulun tilat viihtyisät?", new Questiontype(1L,"text"),questionnaire3);
//			Question question4 = new Question(2L, "Ovatko koulun tilat modernit?", new Questiontype(1L,"text"),questionnaire3);
////			List <Question> questions = List.of(question3);
////			questionnaire3.setQuestions(questions);
//			//Answer(Long answerId, String answerText, Question question
//			Answer answer1 = new Answer(null,"tilat ovat viihtyisät",question3);
//			Answer answer2 = new Answer(null,"tilat ovat modernit",question4);
//
//
//			log.info("Answer1 is: " + answer1);
//
//			question3.setAnswers(List.of(answer1));
//			question4.setAnswers(List.of(answer2));
//			 //configure objectMapper for pretty input
//	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//
//	        //write answerObj object to answer1.json file
//	        objectMapper.writeValue(new File("answer1.json"), List.of(answer1, answer2));
		};
	}
}