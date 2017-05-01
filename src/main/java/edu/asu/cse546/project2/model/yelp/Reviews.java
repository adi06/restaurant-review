package edu.asu.cse546.project2.model.yelp;

import java.util.ArrayList;

public class Reviews {
	private int total;
	private ArrayList<Review> reviews;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
}
