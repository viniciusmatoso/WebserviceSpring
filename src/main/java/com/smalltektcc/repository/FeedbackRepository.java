package com.smalltektcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smalltektcc.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
}
