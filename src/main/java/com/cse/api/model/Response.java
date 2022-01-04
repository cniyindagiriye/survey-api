package com.cse.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
@Entity
@Table(name = "responsess")
public class Response extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;

	@Column(name = "userId", nullable = true)
	public Long userId;

	@Column(name = "surveyId", nullable = false)
	public Long surveyId;

	@Column(name = "questionId", nullable = false)
	public Long questionId;

	@Column(name = "answers", nullable = false)
	public String[] answers;

	public Response() {
		super();
	}
}
