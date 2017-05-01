package edu.asu.cse546.project2.model.yelp;

public class Open {
	private boolean isOvernight;
	private String end;
	private String start;
	private int day;

	public boolean isOvernight() {
		return isOvernight;
	}

	public void setOvernight(boolean isOvernight) {
		this.isOvernight = isOvernight;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}
