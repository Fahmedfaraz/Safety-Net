package com.safetynet.alerts.safetynetalerts.rest.model;

import java.util.List;

public class PersonCount {
private String adultCount;
private String childCount;
private List<StationNumber> personList;
public String getAdultCount() {
	return adultCount;
}
public void setAdultCount(String adultCount) {
	this.adultCount = adultCount;
}
public String getChildCount() {
	return childCount;
}
public void setChildCount(String childCount) {
	this.childCount = childCount;
}
public List<StationNumber> getPersonList() {
	return personList;
}
public void setPersonList(List<StationNumber> personList) {
	this.personList = personList;
}


}
