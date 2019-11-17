package com.app.questionnaire.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Questionnaire {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long questionnaireId;
	
	@Column
	private String questionnaireTitle;
	
	@Column
	private String questionnaireDescription;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
	@JsonIgnore
	private List<Question> questions;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Questionnaire() {
		super();
	}

	public Questionnaire(Long questionnaireId, String questionnaireTitle, String questionnaireDescription,
			List<Question> questions, User user) {
		super();
		this.questionnaireId = questionnaireId;
		this.questionnaireTitle = questionnaireTitle;
		this.questionnaireDescription = questionnaireDescription;
		this.questions = questions;
		this.user = user;
	}
	
	

	public Questionnaire(Long questionnaireId, String questionnaireTitle, String questionnaireDescription, User user) {
		super();
		this.questionnaireId = questionnaireId;
		this.questionnaireTitle = questionnaireTitle;
		this.questionnaireDescription = questionnaireDescription;
		this.user = user;
	}
	
	public Questionnaire(String questionnaireTitle, String questionnaireDescription,
			List<Question> questions, User user) {
		super();
		this.questionnaireTitle = questionnaireTitle;
		this.questionnaireDescription = questionnaireDescription;
		this.questions = questions;
		this.user = user;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getQuestionnaireTitle() {
		return questionnaireTitle;
	}

	public void setQuestionnaireTitle(String questionnaireTitle) {
		this.questionnaireTitle = questionnaireTitle;
	}

	public String getQuestionnaireDescription() {
		return questionnaireDescription;
	}

	public void setQuestionnaireDescription(String questionnaireDescription) {
		this.questionnaireDescription = questionnaireDescription;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Questionnaire [questionnaireId=" + questionnaireId + ", questionnaireTitle=" + questionnaireTitle
				+ ", questionnaireDescription=" + questionnaireDescription + ", questions=" + questions + ", user="
				+ user + "]";
	}
	
	
}
