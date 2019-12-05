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
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Option> options;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnore
	private List<Answer> answers;
	
	

	public Question() {
		super();
	}
	

	public Question(Long questionId, String questionText, Questiontype questiontype, Questionnaire questionnaire,
			List<Answer> answers, List<Option> options) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
		this.questiontype = questiontype;
		this.questionnaire = questionnaire;
		this.answers = answers;
		this.options = options;
	}
	
	public List<Option> getOptions() {
		return options;
	}


	public void setOptions(List<Option> options) {
		this.options = options;
	}


	public Question(Long questionId, String questionText, Questiontype questiontype,
			List<Answer> answers) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
		this.questiontype = questiontype;
		this.answers = answers;
	}

	public Question(Long questionId, String questionText, Questiontype questiontype, Questionnaire questionnaire) {
		super();
		this.questionId = questionId;
		this.questionText = questionText;
		this.questiontype = questiontype;
		this.questionnaire = questionnaire;
	}
	
	public Question(String questionText, Questiontype questiontype, Questionnaire questionnaire) {
		super();
		this.questionText = questionText;
		this.questiontype = questiontype;
		this.questionnaire = questionnaire;
	}
	
	public Question(String questionText, Questiontype questiontype) {
		super();
		this.questionText = questionText;
		this.questiontype = questiontype;
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
				+ questiontype + ", questionnaire=" + questionnaire + ", answers=" + answers + ", options=" + options
				+ "]";
	}

	
	
	

}
