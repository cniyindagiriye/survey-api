package com.cse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cse.api.model.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
	Response findById(String responseId);
}
