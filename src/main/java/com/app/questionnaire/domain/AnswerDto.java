package com.app.questionnaire.domain;

public class AnswerDto {
	
	private Long answerId;
	
	private String answerText;
	
	private Long questionId;
	
	public AnswerDto(Long answerId, String answerText, Long questionId) {
		super();
		this.answerId = answerId;
		this.answerText = answerText;
		this.questionId = questionId;
	}

	public AnswerDto() {
		super();
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "AnswerDto [answerId=" + answerId + ", answerText=" + answerText + ", questionId=" + questionId + "]";
	}

	

}
