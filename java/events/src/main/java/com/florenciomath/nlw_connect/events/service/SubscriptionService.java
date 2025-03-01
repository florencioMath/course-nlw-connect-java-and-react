package com.florenciomath.nlw_connect.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florenciomath.nlw_connect.events.model.Event;
import com.florenciomath.nlw_connect.events.model.Subscription;
import com.florenciomath.nlw_connect.events.model.User;
import com.florenciomath.nlw_connect.events.repository.EventRepository;
import com.florenciomath.nlw_connect.events.repository.SubscriptionRepository;
import com.florenciomath.nlw_connect.events.repository.UserRepository;
import com.florenciomath.nlw_connect.exception.EventNotFoundException;
import com.florenciomath.nlw_connect.exception.SubscriptionConflictException;

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
		
		if (evt == null) {
			throw new EventNotFoundException("Evento " + eventName + " não existe");
		}
		
		User userRec = userRepository.findByEmail(user.getEmail());
		
		if (userRec == null) {
			user = userRepository.save(userRec);			
		}
		
		Subscription subs = new Subscription();
		subs.setEvent(evt);
		subs.setSubscriber(userRec);
		
		Subscription tmbSub = subRepository.findByEventAndSubscriber(evt, userRec);
		 if(tmbSub != null){
			 throw new SubscriptionConflictException("Já existe incrição para o usuário " + userRec.getName() + " no evento " + evt.getTitle());
	     }
		
		
		Subscription res = subRepository.save(subs);
		return res;
	}

}