package com.cse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cse.api.model.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
	Survey findById(String surveyId);
}
