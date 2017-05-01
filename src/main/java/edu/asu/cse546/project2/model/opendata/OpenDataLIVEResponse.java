package edu.asu.cse546.project2.model.opendata;

public class OpenDataLIVEResponse {

private String business_address;
private String business_city;
private String business_id;
private String business_latitude;
private BusinessLocation business_location;

private String business_longitude;
private String business_name;
private String business_postal_code;
private String business_state;
private String inspection_date;
private String inspection_id;
private String inspection_score;
private String inspection_type;
private String risk_category;
private String violation_description;
private String violation_id;


public String getBusinessAddress() {
return business_address;
}

public void setBusinessAddress(String businessAddress) {
this.business_address = businessAddress;
}

public String getBusinessCity() {
return business_city;
}

public void setBusinessCity(String businessCity) {
this.business_city = businessCity;
}

public String getBusinessId() {
return business_id;
}

public void setBusinessId(String businessId) {
this.business_id = businessId;
}

public String getBusinessLatitude() {
return business_latitude;
}

public void setBusinessLatitude(String businessLatitude) {
this.business_latitude = businessLatitude;
}

public BusinessLocation getBusinessLocation() {
return business_location;
}

public void setBusinessLocation(BusinessLocation businessLocation) {
this.business_location = businessLocation;
}

public String getBusinessLongitude() {
return business_longitude;
}

public void setBusinessLongitude(String businessLongitude) {
this.business_longitude = businessLongitude;
}

public String getBusinessName() {
return business_name;
}

public void setBusinessName(String businessName) {
this.business_name = businessName;
}

public String getBusinessPostalCode() {
return business_postal_code;
}

public void setBusinessPostalCode(String businessPostalCode) {
this.business_postal_code = businessPostalCode;
}

public String getBusinessState() {
return business_state;
}

public void setBusinessState(String businessState) {
this.business_state = businessState;
}

public String getInspectionDate() {
return inspection_date;
}

public void setInspectionDate(String inspectionDate) {
this.inspection_date = inspectionDate;
}

public String getInspectionId() {
return inspection_id;
}

public void setInspectionId(String inspectionId) {
this.inspection_id = inspectionId;
}

public String getInspectionScore() {
return inspection_score;
}

public void setInspectionScore(String inspectionScore) {
this.inspection_score = inspectionScore;
}

public String getInspectionType() {
return inspection_type;
}

public void setInspectionType(String inspectionType) {
this.inspection_type = inspectionType;
}

public String getRiskCategory() {
return risk_category;
}

public void setRiskCategory(String riskCategory) {
this.risk_category = riskCategory;
}

public String getViolationDescription() {
return violation_description;
}

public void setViolationDescription(String violationDescription) {
this.violation_description = violationDescription;
}

public String getViolationId() {
return violation_id;
}

public void setViolationId(String violationId) {
this.violation_id = violationId;
}

@Override
public String toString() {
	return "OpenDataLIVEResponse [businessAddress=" + business_address + ", businessCity=" + business_city
			+ ", businessId=" + business_id + ", businessLatitude=" + business_latitude + ", businessLocation="
			+ business_location + ", businessLongitude=" + business_longitude + ", businessName=" + business_name
			+ ", businessPostalCode=" + business_postal_code + ", businessState=" + business_state + ", inspectionDate="
			+ inspection_date + ", inspectionId=" + inspection_id + ", inspectionScore=" + inspection_score
			+ ", inspectionType=" + inspection_type + ", riskCategory=" + risk_category + ", violationDescription="
			+ violation_description + ", violationId=" + violation_id+ "]";
}

}