package com.app.questionnaire.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.questionnaire.domain.Answer;
import com.app.questionnaire.domain.AnswerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class AnswerController {

	private static final Logger log = LoggerFactory.getLogger(AnswerController.class);
	
	//Digi-tiimi lähettää yhden vastaajan vastaukset (JSON) -me otetaan kiinni JSON restissä ja tallennetaan kantaan
	@Autowired
	AnswerRepository aRepository;
	
	@RequestMapping(value="/saveanswer", method = RequestMethod.POST)
	public ResponseEntity<String> saveNewAnswer(String json) {
		
		//luodaan objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		
		//Luetaan json ja muutetaan se answer olioksi
		Answer answer = null;
		try {
			answer = objectMapper.readValue(json, Answer.class);
		}  catch (IOException e) {
			log.error("Jsonparsing failed", e);
			e.printStackTrace();
		}
		
		//tallennetaan uutena Answer oliona
		aRepository.save(answer);
		
		//palautetaan onnistumisviesti fronttiin
		return new  ResponseEntity<String>("Answer received!",HttpStatus.OK);
	}
		
}
