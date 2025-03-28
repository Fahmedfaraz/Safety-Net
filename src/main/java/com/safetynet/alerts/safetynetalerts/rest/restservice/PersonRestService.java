package com.safetynet.alerts.safetynetalerts.rest.restservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.service.DataService;

@RestController
public class PersonRestService {
	@Autowired
	public DataService dataService;
	
	private static final Log logger = LogFactory.getLog(FireStationRestService.class);

	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam(value = "city") String city) {
	
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
