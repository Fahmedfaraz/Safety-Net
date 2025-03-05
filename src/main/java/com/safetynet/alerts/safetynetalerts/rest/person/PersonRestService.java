package com.safetynet.alerts.safetynetalerts.rest.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestService {
	@GetMapping(path="/person")
	String getPerson(){
		return "Test get request";
	}

	
}
