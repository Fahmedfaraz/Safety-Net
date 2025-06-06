package com.safetynet.alerts.safetynetalerts.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder
public class FireStation {
	 private String address;
	 private int station;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}
	public FireStation(String address, int station) {
		super();
		this.address = address;
		this.station = station;
	}
	public FireStation() {
		
	}
	@Override
	public String toString() {
		return "FireStation [address=" + address + ", station=" + station + "]";
	}
	

}
