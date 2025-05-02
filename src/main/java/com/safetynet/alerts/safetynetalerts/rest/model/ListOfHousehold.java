package com.safetynet.alerts.safetynetalerts.rest.model;

import java.util.List;

public class ListOfHousehold {
	private List<MemberOfHousehold> householdMember;
	private String address;
	
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
