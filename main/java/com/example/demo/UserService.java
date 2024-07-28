package com.example.demo;

import com.example.demo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Service
public class UserService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public User register(User user) {
		manager.persist(user);
		return user;
	}
	
	public String login(String email, String password) {
		
		TypedQuery<User> query = manager.createQuery(
				"SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
		
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		User user = query.getResultStream().findFirst().orElse(null);
	 
	    if (user == null)
	    	return "No user found"; 
	
	    else 
	    	return "Login successful"; 
	}
}
