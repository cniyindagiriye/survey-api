package com.cse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cse.api.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findById(String questionId);
}
