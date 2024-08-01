package org.example.carcare.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews") 		// http://localhost:8090/reviews
public class ReviewsController {
	
	@Autowired
	private ReviewsService reviewService;
	
	@PostMapping("/addReview")		// http://localhost:8090/reviews/addReview
	public Reviews addReview(@RequestBody Reviews review) {
		return reviewService.addReview(review);
	}

}

