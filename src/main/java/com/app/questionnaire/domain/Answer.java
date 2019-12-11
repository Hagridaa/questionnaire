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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "answerId")

public class Answer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long answerId;
	
	@Column
	private String answerText;
	
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;
	
	//@JsonManagedReference(value="answer-reference")
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "question")
	private List<Option> options;

	public Answer(Long answerId, Question question, List<Option> options) {
		super();
		this.answerId = answerId;
		this.question = question;
		this.options = options;
	}

	public Answer() {
		super();
	}

	public Answer(Long answerId, String answerText, Question question) {
		super();
		this.answerId = answerId;
		this.answerText = answerText;
		this.question = question;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerText=" + answerText + ", question=" + question + ", options="
				+ options + "]";
	}
	
	
}
