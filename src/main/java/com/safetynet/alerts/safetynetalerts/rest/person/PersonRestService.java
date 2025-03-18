package com.safetynet.alerts.safetynetalerts.rest.person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.firestation.FireStationRestService;

@RestController
public class PersonRestService {
	private static final Log logger = LogFactory.getLog(PersonRestService.class);

	@GetMapping(path="/person")
	public String getPerson(){
		logger.info("This is a DEBUG log");
		return "Test get request";
		
	}
 
	@GetMapping("/GetPersonBean")
	public GetPersonBean getPersonBean(){
		return new GetPersonBean ("Test get request with Constructor");
	
	}
}
