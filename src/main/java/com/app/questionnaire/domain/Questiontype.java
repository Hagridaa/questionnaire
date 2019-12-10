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
public class Questiontype {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long questiontypeId;
	
	@Column
	private String questiontypeName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questiontype")
	@JsonIgnore
	private List<Question> questions;
	
	// options list was not used in the final version of the project
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questiontype")
	@JsonIgnore
	private List<Option> options;
	
	

	public Questiontype(Long questiontypeId, String questiontypeName, List<Question> questions, List<Option> options) {
		super();
		this.questiontypeId = questiontypeId;
		this.questiontypeName = questiontypeName;
		this.questions = questions;
		this.options = options;
	}

	public Questiontype() {
		super();
	}

	public Questiontype(Long questiontypeId, String questiontypeName) {
		super();
		this.questiontypeId = questiontypeId;
		this.questiontypeName = questiontypeName;
	}


	public Long getQuestiontypeId() {
		return questiontypeId;
	}

	public void setQuestiontypeId(Long questiontypeId) {
		this.questiontypeId = questiontypeId;
	}

	public String getQuestiontypeName() {
		return questiontypeName;
	}

	public void setQuestiontypeName(String questiontypeName) {
		this.questiontypeName = questiontypeName;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Questiontype [questiontypeId=" + questiontypeId + ", questiontypeName=" + questiontypeName
				+ ", questions=" + questions + ", options=" + options + "]";
	}

	//@Override
	//public String toString() {
		//return "Questiontype [questiontypeId=" + questiontypeId + ", questiontypeName=" + questiontypeName + "]";
	
	}
