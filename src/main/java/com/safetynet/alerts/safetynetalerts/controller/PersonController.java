package com.safetynet.alerts.safetynetalerts.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonInfo;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	public PersonService personService;
	
	@GetMapping("/personInfo")
	public List<PersonInfo> getPersonInfo(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName)  {
		return personService.getPersonInfo(firstName,lastName);
				 
	}

}
