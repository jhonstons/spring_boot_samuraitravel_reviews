package com.example.samuraitravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;
import com.example.samuraitravel.service.UserService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;
	private final UserService userService;
	private final HouseRepository houseRepository;
	private final ReviewRepository reviewRepository;
	
	@Autowired
	public ReviewController(ReviewService reviewService, UserService userService, HouseRepository houseRepository, ReviewRepository reviewRepository) {
		this.reviewService = reviewService;
		this.userService = userService;
		this.houseRepository = houseRepository;
		this.reviewRepository = reviewRepository;
	}
	
	@GetMapping("/{houseId}")
	public String index(@PathVariable(name = "houseId")Integer houseId, @PageableDefault(page = 0, size = 10, sort = "id")Pageable pageable, Model model){
		House house = houseRepository.getReferenceById(houseId);
		Page<Review>reviewPage = reviewRepository.findByHouseOrderByCreatedAtDesc(house, pageable);
		model.addAttribute("house", house);
		model.addAttribute("reviewPage", reviewPage);
		return "reviews/index";
	}
	
	@GetMapping("/register")
	public String register(@PathVariable(name = "houseId")Integer houseId, Model model) {
		House house = houseRepository.getReferenceById(houseId);
		
		model.addAttribute("house", house);
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
		
		return "reviews/register";
	}
	
	@PostMapping("/create")
	public String create(@PathVariable(name = "houseId")Integer houseId,
		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
		@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("house", houseRepository.getReferenceById(houseId));
			return "reviews/register";
		}
		
		Review review = new Review();
		review.setHouse(houseRepository.getReferenceById(houseId));
		review.setUser(userDetailsImpl.getUser());
		review.setScore(reviewRegisterForm.getScore());
		review.setContent(reviewRegisterForm.getContent());
		
		reviewService.save(review);
		
		redirectAttributes.addFlashAttribute("successMessage", "レビューが登録されました。");
		
		return "redirect:/houses/" + houseId;
	}
	
	
	@GetMapping("/{houseId}/{reviewId}/edit")
	public String edit(@PathVariable(name = "houseId")Integer houseId, @PathVariable(name = "reviewId")Integer reviewId, Model model){
		House house = houseRepository.getReferenceById(houseId);
		Review review = reviewRepository.getReferenceById(reviewId);
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getScore(), review.getContent());
		
		model.addAttribute("house", house);
		model.addAttribute("review", review);
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "reviews/edit";
	}

	@PostMapping("/{houseId}/{reviewId}/update")
	public String update(@PathVariable(name = "houseId")Integer houseId,
		@PathVariable(name = "reviewId")Integer reviewId,
		@ModelAttribute @Validated ReviewEditForm reviewEditForm,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		Model model) {
	
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("house", houseRepository.getReferenceById(houseId));
	        model.addAttribute("review", reviewRepository.getReferenceById(reviewId));
	        return "reviews/edit";
	    }
	
	    Review review = reviewRepository.getReferenceById(reviewId);
	    review.setScore(reviewEditForm.getScore());
	    review.setContent(reviewEditForm.getContent());
	
	    reviewService.save(review);
	
	    redirectAttributes.addFlashAttribute("successMessage", "レビューが更新されました。");
	
	    return "redirect:/houses/" + houseId;
	}
	
	
	@DeleteMapping("/{houseId}/{reviewId}/delete")
	public String delete(@PathVariable(name = "reviewId")Integer reviewId, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(reviewId);
		
		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		
		return "redirect:/houses/{houseId}";
	}
}
