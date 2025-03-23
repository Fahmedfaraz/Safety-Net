package com.safetynet.alerts.safetynetalerts.rest.firestation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.beans.Person;

@RestController
public class FireStationRestService {
	
	private static final Log logger = LogFactory.getLog(FireStationRestService.class);
	
@GetMapping(path="/firestation")
	public List<Person> getFirestation() {
	ArrayList<Person> lstPerson = new ArrayList<Person> ();
	
	logger.trace("getFirestation() has been called");
	
//	Person test= new Person("Faiza","Ahmed");
//	lstPerson.add(test);
	return lstPerson;
}
}


