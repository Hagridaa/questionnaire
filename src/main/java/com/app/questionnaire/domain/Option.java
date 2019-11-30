package com.app.questionnaire.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Option {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long optionId;
	
	private String optionText;
	
	@ManyToOne
	@JoinColumn(name = "questiontypeId")
	private Questiontype questiontype;

	public Option() {
		super();
	}

	public Option(Long optionId, String optionText, Questiontype questiontype) {
		super();
		this.optionId = optionId;
		this.optionText = optionText;
		this.questiontype = questiontype;
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

	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", optionText=" + optionText + ", questiontype=" + questiontype + "]";
	}
	
	
}
