package com.smalltektcc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smalltektcc.event.RecursoCriadoEvent;
import com.smalltektcc.model.Feedback;
import com.smalltektcc.repository.FeedbackRepository;

@RestController
@RequestMapping("/feedback")
public class FeedbackResource {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/{id}")
	public ResponseEntity<Feedback> buscarPeloId(@PathVariable Long id) {
		Feedback feedback = feedbackRepository.findOne(id);
		return feedback != null ? ResponseEntity.ok(feedback) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public List<Feedback> buscarTodos() {
		List<Feedback> feedbackList = feedbackRepository.findAll();
		if (feedbackList != null)
			return feedbackList;
		else
			return (List<Feedback>) ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Feedback> criar(@Valid @RequestBody Feedback feedback, HttpServletResponse response) {
		Feedback feedbackSalvo = feedbackRepository.save(feedback);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, feedbackSalvo.getIdfeedback()));
		return ResponseEntity.status(HttpStatus.CREATED).body(feedbackSalvo);
	}
	
	@PutMapping("/{id}")
	public Feedback updateNote(@PathVariable(value = "id") Long idfeedback, @Valid @RequestBody Feedback usuarioDetails) {

		Feedback feedback = feedbackRepository.findOne(idfeedback);
		
		feedback.setNota(usuarioDetails.getNota());
		
		Feedback updatedFeedback = feedbackRepository.save(feedback);
		return updatedFeedback;
	}
}
