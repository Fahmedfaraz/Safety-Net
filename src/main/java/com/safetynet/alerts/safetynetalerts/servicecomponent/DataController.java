package com.safetynet.alerts.safetynetalerts.servicecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
	
	@Autowired
	private DataService dataService;
	
	@GetMapping ("/data")
	public Data getData(){
		return dataService.getData();
	}
	

}
