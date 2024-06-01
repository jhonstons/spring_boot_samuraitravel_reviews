package com.example.samuraitravel.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> getReviewsByHouseId(Long houseId) {
		return reviewRepository.findByHouseId(houseId);
	}
	
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }	

	@Transactional
	public Review editReview(Long reviewId, Review newReview) {
		return reviewRepository.findById(reviewId).map(review ->{
			review.setComment(newReview.getComment());	
			review.setRating(newReview.getRating());
			return reviewRepository.save(review);
		}).orElseGet(()->{
			newReview.setId(reviewId);
			return reviewRepository.save(newReview);
		});
			
	}
    
}
