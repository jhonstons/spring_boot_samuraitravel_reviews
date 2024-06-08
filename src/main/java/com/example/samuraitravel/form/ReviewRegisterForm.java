package com.example.samuraitravel.form;

public class ReviewRegisterForm {
	private Integer houseId;
	private Integer userId;
	private String name;
	private String content;
	private int score;	

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
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getContent() {
		return content;
	}
	
	public int getScore() {
		return score;
	}

}
