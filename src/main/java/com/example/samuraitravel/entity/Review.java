package com.example.samuraitravel.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "reviews")
public class Review {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@ManyToOne
	@JoinColumn(name = "house_Id", nullable = false)
    private House house;
	
	@ManyToOne
	@JoinColumn(name = "user_Id", nullable = false)
    private User user;
	
	@Column(nullable = false)
	private int score;
	
	@Column(nullable = false)
	private String content;
		
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public String getName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}