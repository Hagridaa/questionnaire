package com.app.questionnaire.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User,Long> {
Optional<User> findByUsername(String username);
//voidaan etsiä tietokannasta usernamella käyttäjiä
User findByUsername2(String username);
}
