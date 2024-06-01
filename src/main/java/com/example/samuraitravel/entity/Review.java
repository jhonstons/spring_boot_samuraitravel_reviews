package com.example.samuraitravel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String comment;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "houses_id")
    private House house;
    
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

}
