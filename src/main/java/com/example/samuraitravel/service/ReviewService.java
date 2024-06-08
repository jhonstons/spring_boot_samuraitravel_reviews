package com.example.samuraitravel.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    
    public ReviewService(ReviewRepository reviewRepository) {
    	this.reviewRepository = reviewRepository;
    }    	
    	
    public List<Review> findByHouse(House house) { 
    	return reviewRepository.findByHouse(house);
	}

	public Review getReviewById(Integer id) {
		return reviewRepository.findById(id).orElse(null);
	}

	public void updateReview(Integer id, ReviewEditForm form) {
		Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
		review.setHouse(new House());
		review.setUser(new User());
		review.setContent(form.getContent());
		review.setScore(form.getScore());
		review.setUpdatedAt(LocalDateTime.now());
		reviewRepository.save(review);
	}

	public void deleteReview(Integer reviewId) {
		reviewRepository.deleteById(reviewId);
	}

	public void saveReview(ReviewRegisterForm form) {
	    Review review = new Review();
	    review.setHouse(new House());
	    review.setUser(new User());
	    review.setContent(form.getContent());
	    review.setScore(form.getScore());
	    review.setCreatedAt(LocalDateTime.now());
	    review.setUpdatedAt(LocalDateTime.now());
	    reviewRepository.save(review);
	}
}
