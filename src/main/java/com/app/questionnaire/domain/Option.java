package com.app.questionnaire.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "optionId")

public class Option {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long optionId;
	
	private String optionText;
	
	//@JsonManagedReference
		//@JsonBackReference
		@ManyToOne
		@JoinColumn(name = "questionId")
		private Question question;
		
		//@JsonBackReference(value="answer-reference")
		//@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "answerId")
		private Answer answer;
	@ManyToOne
	@JoinColumn(name = "questiontypeId")
	private Questiontype questiontype;

	public Option() {
		super();
	}

	public Option(Question question, Answer answer, Long optionId, String optionText, Questiontype questiontype) {
		super();
		this.question = question;
		this.answer = answer;
		this.optionId = optionId;
		this.optionText = optionText;
		this.questiontype = questiontype;
	}



	public Option(Long optionId, String optionText, Questiontype questiontype, Question question) {
		super();
		this.optionId = optionId;
		this.optionText = optionText;
		this.questiontype = questiontype;
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public Questiontype getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(Questiontype questiontype) {
		this.questiontype = questiontype;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Option [question=" + question + ", answer=" + answer + ", optionId=" + optionId + ", optionText="
				+ optionText + ", questiontype=" + questiontype + "]";
	}

	
	
}
