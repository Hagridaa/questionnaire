package com.app.questionnaire.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;


@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// tyhjän lomakkeen muodostaminen
		@RequestMapping(value = "/adduser", method = RequestMethod.GET)
		public String getNewUserForm(Model model) {
			model.addAttribute("user", new User()); //luodaan tyhjä user olio
			return "adduser";
		}
		

		//lomakkeen tietojen vastaanotto ja tallennus kantaa (H2)
		@RequestMapping(value = "/adduser", method = RequestMethod.POST)
		public String add(@Valid User user, BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("user",user);
				return "adduser";
			}
				user.setRole("USER");
				user.setPasswordHass(passwordEncoder.encode(user.getPasswordHass()));
				log.info(user.toString());
				userRepository.save(user);
				model.addAttribute("user", user);
				return ("redirect:login");
		}
	
}
