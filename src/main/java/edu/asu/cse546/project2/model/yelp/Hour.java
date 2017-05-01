package edu.asu.cse546.project2.model.yelp;

import java.util.ArrayList;

public class Hour {
	private boolean isOpenNow;
	private String hoursType;
	private ArrayList<Open> open;

	public boolean isOpenNow() {
		return isOpenNow;
	}

	public void setOpenNow(boolean isOpenNow) {
		this.isOpenNow = isOpenNow;
	}

	public String getHoursType() {
		return hoursType;
	}

	public void setHoursType(String hoursType) {
		this.hoursType = hoursType;
	}

	public ArrayList<Open> getOpen() {
		return open;
	}

	public void setOpen(ArrayList<Open> open) {
		this.open = open;
	}
}
