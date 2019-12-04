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
	
	@RequestMapping(value="/allanswers", method = RequestMethod.GET)
	public Iterable<Answer> findAllAnswers() {
		return aRepository.findallAnswers();
	}
	
	@RequestMapping(value="/saveanswer", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> saveNewAnswer(@RequestBody String json) {
		log.info(json.toString());
		//luodaan objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		
		//Luetaan json ja muutetaan se answer olioiksi
		try {
			List<Answer> answers = objectMapper.readValue(json, new TypeReference<List<Answer>>(){});
				log.info(answers.toString());
				int i = 0;
				while (i < answers.size()) {
				  log.info("Tallennettava vastaus: " + answers.get(i).getAnswerText());
				  aRepository.save(answers.get(i));
				  i++;
				}			
		}  catch (IOException e) {
			log.error("Jsonparsing failed", e);
		}

		//palautetaan onnistumisviesti fronttiin
		return new  ResponseEntity<String>("Answer received!",HttpStatus.OK);
	}
}
