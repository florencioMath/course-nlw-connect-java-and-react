package com.florenciomath.nlw_connect.events.repository;

import org.springframework.data.repository.CrudRepository;

import com.florenciomath.nlw_connect.events.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findByEmail(String email);

}
