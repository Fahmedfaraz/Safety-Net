package com.safetynet.alerts.safetynetalerts.rest.model;

import java.util.List;

public class ListOfHousehold {
	private String address;
	private List<MemberOfHousehold> householdMember;
	
	
	public List<MemberOfHousehold> getHouseholdMember() {
		return householdMember;
	}
	public void setHouseholdMember(List<MemberOfHousehold> householdMember) {
		this.householdMember = householdMember;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
