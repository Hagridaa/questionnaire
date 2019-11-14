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

	@Override
	public String toString() {
		return "Questiontype [questiontypeId=" + questiontypeId + ", questiontypeName=" + questiontypeName + "]";
	}

}
