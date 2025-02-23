package com.florenciomath.nlw_connect.events.repository;

import org.springframework.data.repository.CrudRepository;

import com.florenciomath.nlw_connect.events.model.Event;

public interface EventRepository extends CrudRepository<Event, Integer>{

}
