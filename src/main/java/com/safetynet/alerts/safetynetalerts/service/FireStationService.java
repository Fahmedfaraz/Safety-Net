package com.safetynet.alerts.safetynetalerts.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonAddress;

@Service
public class FireStationService {
	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(FireStationService.class);

//	 This function return a list of phone numbers of each person within the fire station’s jurisdiction
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

	// This function return fire station number , each person’s name, phone number,
	// age, medications with dosage, and allergies at a particular address.
	public List<PersonAddress> getFireStationNumber(String address) throws UnsupportedEncodingException {
		address = URLDecoder.decode(address, "UTF-8");
		PersonService ps = new PersonService();
		List<PersonAddress> personAddressInfoList = new ArrayList<PersonAddress>();
		for (FireStation fireStation : dataService.getFirestations()) {

			if (fireStation.getAddress().equals(address)) {

				for (Person person : dataService.getPersons()) {
					if (person.getAddress().equals(address)) {
						PersonAddress personAddress = new PersonAddress();
						personAddress.setStation(fireStation.getStation());
						personAddress.setFirstName(person.getFirstName());
						personAddress.setLastName(person.getLastName());
						personAddress.setPhone(person.getPhone());

						for (MedicalRecord mRecord : dataService.getMedicalrecords()) {
							if (mRecord.getFirstName().equals(person.getFirstName())
									& mRecord.getLastName().equals(person.getLastName())) {
								personAddress.setAge(ps.getAge(mRecord.getBirthdate()));
								personAddress.setMedications(mRecord.getMedications());
								personAddress.setAllergies(mRecord.getAllergies());
							}
						}
						personAddressInfoList.add(personAddress);
					}
				}
				break;
			}
		}
		return personAddressInfoList;
	}

	public List<FireStation> getAllFirestation() {
		return dataService.getFirestations();
	}

	public void addNewfirestation(FireStation fireStation) {
		dataService.getFirestations().add(fireStation);
	}

	public void deleteFireStationAddress(String address) {
		for (FireStation fireStation : dataService.getFirestations()) {

			if (fireStation.getAddress().equals(address)) {
				dataService.getFirestations().remove(address);
				break;
			}
			}
		
	}

	public FireStation updateFireStationNum(FireStation updatefireStation) {
		for (FireStation fireStation : dataService.getFirestations()) {
			if(fireStation.getAddress().equalsIgnoreCase(updatefireStation.getAddress())) {
				fireStation.setStation(updatefireStation.getStation());
				break;
			}
		}
		return updatefireStation;
	}

	

	
}