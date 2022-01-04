package com.cse.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cse.api.exception.ResourceNotFoundException;
import com.cse.api.model.Survey;
import com.cse.api.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SurveyController {

  @Autowired
  private SurveyRepository surveyRepository;

  @PostMapping("/surveys")
  public Survey createSurvey(@Valid @RequestBody Survey survey) throws ResourceNotFoundException {
    return surveyRepository.save(survey);
  }

  @GetMapping("/surveys")
  public List<Survey> getAllsurveys() {
    return surveyRepository.findAll();
  }

  @GetMapping("/surveys/{id}")
  public ResponseEntity<Survey> getSurveyById(@PathVariable(value = "id") Long surveyId)
      throws ResourceNotFoundException {
    Survey survey = surveyRepository.findById(surveyId)
        .orElseThrow(() -> new ResourceNotFoundException("survey not found :: " + surveyId));
    return ResponseEntity.ok().body(survey);
  }

  @DeleteMapping("/surveys/{id}")
  public Map<String, Boolean> deletContact(@PathVariable(value = "id") Long surveyId)
      throws ResourceNotFoundException {
    Survey survey = surveyRepository.findById(surveyId)
        .orElseThrow(() -> new ResourceNotFoundException("Survey not found :: " + surveyId));
    surveyRepository.delete(survey);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

  @PutMapping("/surveys/{id}")
  public ResponseEntity<Survey> updateSurvey(@PathVariable(value = "id") Long surveyId,
      @Valid @RequestBody Survey surveyDetails) throws ResourceNotFoundException {
    Survey survey = surveyRepository.findById(surveyId)
        .orElseThrow(() -> new ResourceNotFoundException("survey not found :: " + surveyId));
    survey.userId = surveyDetails.userId;
    survey.title = surveyDetails.title;
    final Survey updatedResponse = surveyRepository.save(survey);
    return ResponseEntity.ok(updatedResponse);
  }

}
