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
import com.app.questionnaire.domain.Option;
import com.app.questionnaire.domain.OptionRepository;
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
	public CommandLineRunner Demo(QuestiontypeRepository questionTypeRepository, UserRepository userRepository, QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswerRepository aRepository, OptionRepository oRepository) { 
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
			
			Questionnaire questionnaire1 = new Questionnaire(null, "Tutkimme Haaga-Helian kurssitarjonnan työelämäyhteyttä sekä opiskelijoiden tyytyväisyyttä kurssivalikoimaan", "Kyselyyn vastaaminen vie 2-3 minuuttia. Kiitos arvokkaista vastauksistasi!",questionlist1,user1);
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
			Questiontype questionType2 = new Questiontype(null,"option");
			Questiontype questionType3 = new Questiontype(null,"checkbox");
			Questiontype questionType4 = new Questiontype(null,"Skaala 1-10");
			questionTypeRepository.save(questionType1);
			questionTypeRepository.save(questionType2);
			questionTypeRepository.save(questionType3);
			questionTypeRepository.save(questionType4);
			
			
			
			Question question1 = new Question(null, "Miten kurssien työelämäyhteys tuotu esille?", questionType1, questionnaire1);
			Question question2 = new Question(null,"Oletko suorittanut kursseja?", questionType2, questionnaire1);
			Question question3 = new Question(null,"Mikä näistä kuvaa parhaiten Haaga-Helian kurssitarjontaa?", questionType3, questionnaire1);
			Question question4 = new Question(null, "Mitä toivoisit Haaga-Helian kurssitarjonnalta?", questionType1, questionnaire1);
			Question question5 = new Question(null, "Kuinka todennäköisesti suosittelisit Haaga-Heliaa? (1 = en suosittele lainkaan, 10 = suosittelen lämpimästi)", questionType4, questionnaire1);
			
			questionRepository.save(question1);
			questionRepository.save(question2);
			questionRepository.save(question3);
			questionRepository.save(question4);
			questionRepository.save(question5);
			
			//questionlist1.add(question1);
			Option option30 = new Option(null, "Kurssilla on käyty yritysvierailulla",questionType2, question1);
			Option option31 = new Option(null, "Yrityksen edustaja on käynyt luennoimassa kurssin aiheesta",questionType2, question1);
			Option option32 = new Option(null, "Kurssin toimeksiantajana on ollut yritys",questionType2, question1);
			Option option33 = new Option(null, "Opettaja on kertonut esimerkkejä yrityksistä, joissa opeteltavat metodit ovat käytössä",questionType2, question1);
			Option option34 = new Option(null, "Muu, mikä?",questionType2, question1);
			oRepository.save(option30);
			List<Option> optionlist4 = List.of(option30,option31,option32,option33,option34);
			
			//Luodaan uusi option lista
			//Long optionId, String optionText, Questiontype questiontype
			Option option1 = new Option(null, "Campusonline.fi:n kautta",questionType2, question2);
			Option option2 = new Option(null, "3AMK:n kautta",questionType2, question2);
			Option option3 = new Option(null, "Virtuaalisesti",questionType2, question2);
			Option option4 = new Option(null, "Helsingin seudun kesäyliopiston kautta",questionType2, question2);
			Option option5 = new Option(null, "Ei mikään yllämainituista",questionType2, question2);
			Option option6 = new Option(null, "Muu, mikä?",questionType2, question2);
			oRepository.save(option1);
			oRepository.save(option2);
			oRepository.save(option3);
			oRepository.save(option4);
			oRepository.save(option5);
			oRepository.save(option6);
			log.info("Test options are: " + option1 + option2 );
			List<Option> optionlist1 = List.of(option1,option2,option3,option4,option5,option6);
			log.info("Test optionlist is: " + optionlist1);

			//checkbox kysymys vastausvaihtoehdot
			Option option7 = new Option(null, ":D",questionType3, question3);
			Option option8 = new Option(null, ":)",questionType3, question3);
			Option option9 = new Option(null, ":I",questionType3, question3);
			Option option10 = new Option(null, ":(",questionType3, question3);
			oRepository.save(option7);
			oRepository.save(option8);
			oRepository.save(option9);
			oRepository.save(option10);
			List<Option> optionlist2 = List.of(option7,option8,option9,option10);
			log.info("Test optionlist is for checkbox: " + optionlist2);
			
			//skaala 1-10 vastausvaihtoehdot
			Option option11 = new Option(null, "1",questionType4, question5);
			Option option12 = new Option(null, "2",questionType4, question5);
			Option option13 = new Option(null, "3",questionType4, question5);
			Option option14 = new Option(null, "4",questionType4, question5);
			Option option15 = new Option(null, "5",questionType4, question5);
			Option option16 = new Option(null, "6",questionType4, question5);
			Option option17 = new Option(null, "7",questionType4, question5);
			Option option18 = new Option(null, "8",questionType4, question5);
			Option option19 = new Option(null, "9",questionType4, question5);
			Option option20 = new Option(null, "10",questionType4, question5);
			oRepository.save(option11);
			oRepository.save(option12);
			oRepository.save(option13);
			oRepository.save(option14);
			oRepository.save(option15);
			oRepository.save(option16);
			oRepository.save(option17);
			oRepository.save(option18);
			oRepository.save(option19);
			oRepository.save(option20);
			List<Option> optionlist3 = List.of(option11,option12,option13,option14,option16,option17,option18,option19,option20);
			
			
			
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
			Answer answer3 = new Answer(null,"Kurssilla on käyty yritysvierailulla",question1);
			Answer answer4 = new Answer(null,"Campusonline.fi:n kautta",question2);
			aRepository.save(answer3);
			aRepository.save(answer4);
			
			log.info("Answer1 is: " + answer4);

			question1.setAnswers(List.of(answer3));
			question2.setAnswers(List.of(answer4));
			 //configure objectMapper for pretty input
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

	        //write answerObj object to answer1.json file
	        
	        objectMapper.writeValue(new File("answer2.json"), List.of(answer3, answer4));
	        
		};
	}
}