package com.florenciomath.nlw_connect.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.florenciomath.nlw_connect.events.model.Subscription;
import com.florenciomath.nlw_connect.events.model.User;
import com.florenciomath.nlw_connect.events.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;
	
	@PostMapping("/subscription/{prettyName}")
	public ResponseEntity<Subscription> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber) {
		Subscription res = service.createNewSubscription(prettyName, subscriber);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.badRequest().build();
	}
}