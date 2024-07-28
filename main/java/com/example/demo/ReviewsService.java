package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ReviewsService {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Reviews addReview(Reviews review) {
		manager.persist(review);
		return review;
	}
}

