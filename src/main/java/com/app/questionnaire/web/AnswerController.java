package com.app.questionnaire.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.questionnaire.domain.Answer;
import com.app.questionnaire.domain.AnswerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class AnswerController {

	private static final Logger log = LoggerFactory.getLogger(AnswerController.class);
	
	//Digi-tiimi lähettää yhden vastaajan vastaukset (JSON) -me otetaan kiinni JSON restissä ja tallennetaan kantaan
	@Autowired
	AnswerRepository aRepository;
	
	@RequestMapping(value="/saveanswer", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> saveNewAnswer(@RequestBody String json) {
		
		log.info(json.toString());
		//luodaan objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		
		//Luetaan json ja muutetaan se answer olioksi
		List<Answer> answers = null;
		try {
				answers = objectMapper.readValue(json, new TypeReference<List<Answer>>(){});
				int i = 0;
				while (i < answers.size()) {
				  System.out.println(i);
				  aRepository.save(answers.get(i));
				  i++;
				}
						
		}  catch (IOException e) {
			log.error("Jsonparsing failed", e);
			e.printStackTrace();
		}
		
//		int i = 0;
//		while (i < json.size()) {
//		  System.out.println(i);
//		  aRepository.save(json.get(i));
//		  i++;
//		}
		
		log.info("frontista saatu tallennettava vastauslista on: ",json.toString());
		//tallennetaan uutena Answer oliona
		
		Iterable<Answer> foundedAnswers = aRepository.findAll();
		log.info("founded answers: " ,foundedAnswers.toString());
		//palautetaan onnistumisviesti fronttiin
		return new  ResponseEntity<String>("Answer received!",HttpStatus.OK);
	}
		
}
