package edu.asu.cse546.project2.model.opendata;

import java.util.List;

public class BusinessLocation {

private String type;
private List<Double> coordinates = null;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public List<Double> getCoordinates() {
return coordinates;
}

public void setCoordinates(List<Double> coordinates) {
this.coordinates = coordinates;
}

@Override
public String toString() {
	return "BusinessLocation [type=" + type + ", coordinates=" + coordinates + "]";
}

}