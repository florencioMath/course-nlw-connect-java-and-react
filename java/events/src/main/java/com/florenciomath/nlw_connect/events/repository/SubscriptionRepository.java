package com.florenciomath.nlw_connect.events.repository;

import org.springframework.data.repository.CrudRepository;

import com.florenciomath.nlw_connect.events.model.Event;
import com.florenciomath.nlw_connect.events.model.Subscription;
import com.florenciomath.nlw_connect.events.model.User;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>{
	
	public Subscription findByEventAndSubscriber(Event evt, User user);

}
