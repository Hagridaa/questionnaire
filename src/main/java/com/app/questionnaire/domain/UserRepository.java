package com.app.questionnaire.domain;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
User findByUsername(String username);
//voidaan etsiä tietokannasta usernamella käyttäjiä
}
