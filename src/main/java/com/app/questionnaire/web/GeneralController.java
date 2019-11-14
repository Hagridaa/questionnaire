package com.app.questionnaire.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

		// Homepage
		@RequestMapping(value="/")
		public String home() {
			return "homepage";
		}
		
		// REST homepage
		@RequestMapping(value="/resthomepage")
		public String resthomepage() {
			return "resthomepage";
		}
}
