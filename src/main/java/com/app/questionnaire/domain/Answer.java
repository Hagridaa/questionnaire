package com.app.questionnaire.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
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

	public Answer() {
		super();
	}

	public Answer(Long answerId, String answerText, Question question) {
		super();
		this.answerId = answerId;
		this.answerText = answerText;
		this.question = question;
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
		return "Answer [answerId=" + answerId + ", answerText=" + answerText + ", question=" + question + "]";
	}
	
	
}
