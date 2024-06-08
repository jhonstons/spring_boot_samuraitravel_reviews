package com.example.samuraitravel.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.service.ReviewService;
import com.example.samuraitravel.service.UserService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;
	private final UserService userService;
	
	@Autowired
	public ReviewController(ReviewService reviewService, UserService userService) {
		this.reviewService = reviewService;
		this.userService = userService;
	}
		
	@GetMapping("/house/{house_id}")
	public String getReviews(@PathVariable Integer house_id, Model model) {
		House house = new House();
		house.setId(house_id);
		List<Review> reviews = reviewService.findByHouse(house);
		model.addAttribute("reviews", reviews);
		return "reviews/index";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm ());
		return "reviews/register";			
	}
	
	@PostMapping("/register")
	public String registerReview(@ModelAttribute ReviewRegisterForm form, Principal principal) {
	    if (principal == null || !isUserMember(principal.getName())) {
	        return "redirect:/login";
	    }
	    reviewService.saveReview(form);
	    return "redirect:/reviews/house/" + form.getHouseId();
	}

    private boolean isUserMember(String email) {
        User user = userService.findByEmail(email);
        return user != null && user.getRole().getName().contains("ROLE_MEMBER");
    }
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
		Review review = reviewService.getReviewById(id);
		ReviewEditForm form = new ReviewEditForm();
		form.setId(review.getId());
		form.setHouseId(review.getHouse().getId());
		form.setUserId(review.getUser().getId());
		form.setName(review.getName());
		form.setContent(review.getContent());
		form.setScore(review.getScore());
		model.addAttribute("reviewEditForm", form);
		return "reviews/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String editReview(@PathVariable Integer id, @ModelAttribute ReviewEditForm form) {
		reviewService.updateReview(id, form);
		return "redirect:/reviews/house/" + form.getHouseId();
	}
	
    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/reviews";
    }	

}
