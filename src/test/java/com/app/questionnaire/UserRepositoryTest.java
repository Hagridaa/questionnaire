package com.app.questionnaire;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;

//annotation tells JUnit to run using Spring’s testing support
@RunWith(SpringRunner.class)
@DataJpaTest 
//annotation will configure in-memory database, JPA and Spring
//Data. It also turns on SQL logging
public class UserRepositoryTest {
	
	//This annotation allows Spring to resolve
		//and inject collaborating beans into your bean.
		@Autowired
		private UserRepository userRepository;
		
		@Test
	    public void findByName() {
		  Optional<User> users = userRepository.findByUsername("user");
	        assertThat(users.get().getUsername()).isEqualTo("user");
	        
	  }
		
		@Test
		public void createNewUser() {
			User user1 = new User(null, "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","pii@gmail.com");
			userRepository.save(user1);
			assertThat(user1.getUsername()).isNotNull();
			assertThat(user1.getUsername()).startsWith("u").endsWith("r");
		}

}
