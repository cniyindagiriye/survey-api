package com.cse.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cse.api.exception.ResourceNotFoundException;
import com.cse.api.model.Response;
import com.cse.api.repository.ResponseRepository;

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
public class ResponseController {

  @Autowired
  private ResponseRepository responseRepository;

  @PostMapping("/responses")
  public Response createResponse(@Valid @RequestBody Response response) throws ResourceNotFoundException {
    return responseRepository.save(response);
  }

  @GetMapping("/responses")
  public List<Response> getAllresponses() {
    return responseRepository.findAll();
  }

  @GetMapping("/responses/{id}")
  public ResponseEntity<Response> getResponseById(@PathVariable(value = "id") Long responseId)
      throws ResourceNotFoundException {
    Response response = responseRepository.findById(responseId)
        .orElseThrow(() -> new ResourceNotFoundException("question not found :: " + responseId));
    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/responses/{id}")
  public Map<String, Boolean> deletContact(@PathVariable(value = "id") Long responseId)
      throws ResourceNotFoundException {
    Response response = responseRepository.findById(responseId)
        .orElseThrow(() -> new ResourceNotFoundException("Contact not found :: " + responseId));
    responseRepository.delete(response);
    Map<String, Boolean> res = new HashMap<>();
    res.put("deleted", Boolean.TRUE);
    return res;
  }

  @PutMapping("/responses/{id}")
  public ResponseEntity<Response> updateResponse(@PathVariable(value = "id") Long responseId,
      @Valid @RequestBody Response responseDetails) throws ResourceNotFoundException {
    Response response = responseRepository.findById(responseId)
        .orElseThrow(() -> new ResourceNotFoundException("response not found :: " + responseId));
    response.answers = responseDetails.answers;
    response.questionId = responseDetails.questionId;
    response.userId = responseDetails.userId;
    final Response updatedResponse = responseRepository.save(response);
    return ResponseEntity.ok(updatedResponse);
  }

}
