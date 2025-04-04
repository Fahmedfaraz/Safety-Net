package com.safetynet.alerts.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.safetynet.alerts.safetynetalerts.controller.CommunityEmailController;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
@Service
public class CommunityEmailService {
	
	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(CommunityEmailService.class);
	
	public List<String> getCommunityEmail(String city) {
		
		List<String> communityEmail = new ArrayList<String>();
//		List<Person> personsList = dataService.getPersons();
//		for (Person person : personsList) {

		for (Person person : dataService.getPersons()) {
			if (person.getCity().equalsIgnoreCase(city)) {
				communityEmail.add(person.getEmail());
			}
		}

		if (communityEmail != null) {
			logger.info("This will return the email addresses of all of the people in the city.");
			
		} else
			logger.error("City not found");
		return communityEmail;
	}
}
