package com.florenciomath.nlw_connect.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florenciomath.nlw_connect.events.model.Event;
import com.florenciomath.nlw_connect.events.model.Subscription;
import com.florenciomath.nlw_connect.events.model.User;
import com.florenciomath.nlw_connect.events.repository.EventRepository;
import com.florenciomath.nlw_connect.events.repository.SubscriptionRepository;
import com.florenciomath.nlw_connect.events.repository.UserRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private EventRepository evtRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubscriptionRepository subRepository;

	public Subscription createNewSubscription(String eventName, User user) {
		
		Event evt = evtRepository.findByPrettyName(eventName);
		user = userRepository.save(user);
		
		Subscription subs = new Subscription();
		subs.setEvent(evt);
		subs.setSubscriber(user);
		
		
		Subscription res = subRepository.save(subs);
		return res;
	}

}