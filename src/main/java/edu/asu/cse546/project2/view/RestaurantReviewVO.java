package edu.asu.cse546.project2.view;

import java.util.ArrayList;

import edu.asu.cse546.project2.model.yelp.Coordinates;
import edu.asu.cse546.project2.model.yelp.Review;

public class RestaurantReviewVO {
	
	private float score;
	private String riskCategory;
	private String violation;
	private ArrayList<Review> reviews;
	private String imageUrl;
	private Coordinates coordinates;
	private ArrayList<String> displayAddress;
	private String name;
	private double rating;
	
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getRiskCategory() {
		return riskCategory;
	}
	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}
	public String getViolation() {
		return violation;
	}
	public void setViolation(String violation) {
		this.violation = violation;
	}
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public ArrayList<String> getDisplayAddress() {
		return displayAddress;
	}
	public void setDisplayAddress(ArrayList<String> displayAddress) {
		this.displayAddress = displayAddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
