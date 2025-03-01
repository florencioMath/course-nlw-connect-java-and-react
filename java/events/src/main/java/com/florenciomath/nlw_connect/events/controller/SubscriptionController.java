package com.florenciomath.nlw_connect.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.florenciomath.nlw_connect.dto.ErrorMessage;
import com.florenciomath.nlw_connect.events.model.Subscription;
import com.florenciomath.nlw_connect.events.model.User;
import com.florenciomath.nlw_connect.events.service.SubscriptionService;
import com.florenciomath.nlw_connect.exception.EventNotFoundException;
import com.florenciomath.nlw_connect.exception.SubscriptionConflictException;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;
	
	@PostMapping("/subscription/{prettyName}")
	public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber) {
		try {
			Subscription res = service.createNewSubscription(prettyName, subscriber);
			if (res != null) {
				return ResponseEntity.ok(res);
			}
			
		} catch (EventNotFoundException ex) {
			return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
		} catch (SubscriptionConflictException ex) {
			return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
		}
		return ResponseEntity.badRequest().build();
	}
}