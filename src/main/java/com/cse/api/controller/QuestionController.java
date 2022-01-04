package com.cse.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cse.api.exception.ResourceNotFoundException;
import com.cse.api.model.Question;
import com.cse.api.repository.QuestionRepository;

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
public class QuestionController {

  @Autowired
  private QuestionRepository questionRepository;

  @PostMapping("/questions")
  public Question createQuestion(@Valid @RequestBody Question question) throws ResourceNotFoundException {
    return questionRepository.save(question);
  }

  @GetMapping("/questions")
  public List<Question> getAllquestions() {
    return questionRepository.findAll();
  }

  @GetMapping("/questions/{id}")
  public ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Long questionId)
      throws ResourceNotFoundException {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new ResourceNotFoundException("question not found :: " + questionId));
    return ResponseEntity.ok().body(question);
  }

  @DeleteMapping("/questions/{id}")
  public Map<String, Boolean> deletContact(@PathVariable(value = "id") Long questionId)
      throws ResourceNotFoundException {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new ResourceNotFoundException("Contact not found :: " + questionId));
    questionRepository.delete(question);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

  @PutMapping("/questions/{id}")
  public ResponseEntity<Question> updateQuestion(@PathVariable(value = "id") Long questionId,
      @Valid @RequestBody Question questionDetails) throws ResourceNotFoundException {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new ResourceNotFoundException("question not found :: " + questionId));
    question.answers = questionDetails.answers;
    question.question = questionDetails.question;
    question.surveyId = questionDetails.surveyId;
    final Question updatedResponse = questionRepository.save(question);
    return ResponseEntity.ok(updatedResponse);
  }

}
