package com.example.samuraitravel.form;

public class ReviewEditForm {
    private Integer id;
    private Integer houseId;
    private Integer userId;
    private String name;
    private String content;
    private int score;

    public Integer getId() {
    	return id;
    }

    public void setId(Integer id) {
    	this.id = id;
    }
    
    public Integer getHouseId() {
    	return houseId;
    }
    
    public void setHouseId(Integer houseId) {
    	this.houseId = houseId;
    }
    
    public Integer getUserId() {
    	return userId;
    }
    
    public void setUserId(Integer userId) {
    	this.userId = userId;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String Name) {
    	this.name = Name;
    }
    
    public String getContent() {
    	return content;
    }
    
    public void setContent(String content) {
    	this.content = content;
    }

    public int getScore() {
    	return score;
    }
    
    public void setScore(int score) {
    	this.score = score;
    }
    
}
