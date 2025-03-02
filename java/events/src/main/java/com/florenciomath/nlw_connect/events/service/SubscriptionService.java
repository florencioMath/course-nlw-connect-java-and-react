package com.florenciomath.nlw_connect.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florenciomath.nlw_connect.dto.SubscriptionRankingItem;
import com.florenciomath.nlw_connect.dto.SubscriptionResponse;
import com.florenciomath.nlw_connect.events.model.Event;
import com.florenciomath.nlw_connect.events.model.Subscription;
import com.florenciomath.nlw_connect.events.model.User;
import com.florenciomath.nlw_connect.events.repository.EventRepository;
import com.florenciomath.nlw_connect.events.repository.SubscriptionRepository;
import com.florenciomath.nlw_connect.events.repository.UserRepository;
import com.florenciomath.nlw_connect.exception.EventNotFoundException;
import com.florenciomath.nlw_connect.exception.SubscriptionConflictException;
import com.florenciomath.nlw_connect.exception.UserIndicadorNotFoundException;

@Service
public class SubscriptionService {
	
	@Autowired
	private EventRepository evtRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubscriptionRepository subRepository;

	public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
		
		Event evt = evtRepository.findByPrettyName(eventName);
		if (evt == null) {
			throw new EventNotFoundException("Evento " + eventName + " não existe");
		}
		
		User userRec = userRepository.findByEmail(user.getEmail());
		if (userRec == null) {
			userRec = userRepository.save(user);			
		}
		
		User indicador = null;
		if (userId != null) {
			indicador = userRepository.findById(userId).orElse(null);
			if (indicador == null) {
				throw new UserIndicadorNotFoundException("Usuário " + userId + " não existe");
			}
		}
		
		Subscription subs = new Subscription();
		subs.setEvent(evt);
		subs.setSubscriber(userRec);
		subs.setIndication(indicador);
		
		Subscription tmbSub = subRepository.findByEventAndSubscriber(evt, userRec);
		 if(tmbSub != null){
			 throw new SubscriptionConflictException("Já existe incrição para o usuário " + userRec.getName() + " no evento " + evt.getTitle());
	     }
		
		
		 Subscription res = subRepository.save(subs);
	     return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/subscription/"+res.getEvent().getPrettyName()+"/"+res.getSubscriber().getId());
	}

	public List<SubscriptionRankingItem> getCompleteRanking(String prettyName) {
		Event evt = evtRepository.findByPrettyName(prettyName);
		if (evt == null) {
			throw new EventNotFoundException(prettyName);
		}
		
		return subRepository.generateRanking(evt.getEventId());
	}
}