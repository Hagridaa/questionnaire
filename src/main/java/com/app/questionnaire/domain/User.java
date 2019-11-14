package com.app.questionnaire.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long userId;
	
	@Column
	private String username;
	
	@Column
	private String passwordHass;
	//koska salasana kryptataan
	
	@Column(name = "role", nullable = false)
	private String role;
	//lisäsin tämän jotta voidaan antaa eri rooleja
	
	@Column
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Questionnaire> questionnaires;

	public User() {
		super();
	}

	public User(Long userId, String username, String passwordHass, String role, String email,
			List<Questionnaire> questionnares) {
		super();
		this.userId = userId;
		this.username = username;
		this.passwordHass = passwordHass;
		this.role = role;
		this.email = email;
		this.questionnaires = questionnaires;
	}
	
	

	public User(Long userId, String username, String passwordHass, String role, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.passwordHass = passwordHass;
		this.role = role;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHass() {
		return passwordHass;
	}

	public void setPasswordHass(String passwordHass) {
		this.passwordHass = passwordHass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnares(List<Questionnaire> questionnares) {
		this.questionnaires = questionnares;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passwordHass=" + passwordHass + ", role=" + role
				+ ", email=" + email + ", questionnaires=" + questionnaires + "]";
	}
	
	
	
}


