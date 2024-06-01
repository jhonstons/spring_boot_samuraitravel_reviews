package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/house/{houseId}")
	public List<Review> getReviews(@PathVariable Long houseId) {
		return reviewService.getReviewsByHouseId(houseId);
	}

	@PostMapping
	public Review addReview(@RequestBody Review review) {
		return reviewService.saveReview(review);
	}
	
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }	
    
    @PutMapping("/{reviewId}")
    public Review editReview(@PathVariable Long reviewId, @RequestBody Review newReview) {
    return reviewService.editReview(reviewId, newReview);	
    }
}
