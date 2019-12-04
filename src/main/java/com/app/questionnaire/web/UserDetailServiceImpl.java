package com.app.questionnaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.questionnaire.domain.User;
import com.app.questionnaire.domain.UserRepository;

//this class is used by spring security to authenticate ans authorize user

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	 private final UserRepository repository;
	 
@Autowired public UserDetailServiceImpl(UserRepository userRepository) {
	    	this.repository = userRepository;

}
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	User curruser = repository.findByUsername(username);
	UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHass(),
			AuthorityUtils.createAuthorityList(curruser.getRole()));
	return user;
}
}

