package com.safetynet.alerts.safetynetalerts.rest.person;

public class GetPersonBean {

	private String message;

	public GetPersonBean(String message) {
		this.message=message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "GetPersonBean [message=" + message + "]";
	}

	
}
