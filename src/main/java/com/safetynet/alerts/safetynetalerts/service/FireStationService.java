package com.safetynet.alerts.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;

@Service
public class FireStationService {
	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(FireStationService.class);

	public List<String> getPhoneNumbers(int firestation) {
		List<String> phoneNumbers = new ArrayList<String>();
		String address;
		for (FireStation fireStation : dataService.getFirestations()) {
			if (fireStation.getStation() == firestation) {
				address = fireStation.getAddress();
				for (Person person : dataService.getPersons()) {
					if (person.getAddress().equals(address)) {
						phoneNumbers.add(person.getPhone());
					}
				}
			}
		}
		return phoneNumbers;

	}
}
