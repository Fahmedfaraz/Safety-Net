package com.safetynet.alerts.safetynetalerts.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.model.PersonAddress;
import com.safetynet.alerts.safetynetalerts.service.FireStationService;

@RestController
public class FireStationController {
	
	@Autowired
	public FireStationService fireStationService;
	
	private static final Log logger = LogFactory.getLog(FireStationController.class);
	
@GetMapping(path="/phoneAlert")
	public List<String> getPhoneNumber(@RequestParam(value = "firestation") int firestation) {
		return fireStationService.getPhoneNumbers(firestation);
}

@GetMapping(path="/fire")
public List<PersonAddress> getFireStationNumber(@RequestParam(value = "address") String address) throws UnsupportedEncodingException {
	return fireStationService.getFireStationNumber(address);
}
}


