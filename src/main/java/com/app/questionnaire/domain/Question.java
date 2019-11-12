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
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long questionId;
	
	@Column
	private String questionText;
	
	@ManyToOne
	@JoinColumn(name = "questiontypeId")
	private Questiontype questiontype;
	
	@ManyToOne
	@JoinColumn(name = "questionnaireId")
	private Questionnaire questionnaire;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnore
	private List<Answer> answers;

	public Question() {
		super();
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Questiontype getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(Questiontype questiontype) {
		this.questiontype = questiontype;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionText=" + questionText + ", questiontype="
				+ questiontype + ", questionnaire=" + questionnaire + ", answers=" + answers + "]";
	}
	
	

}
