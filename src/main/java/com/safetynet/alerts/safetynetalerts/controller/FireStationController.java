package com.safetynet.alerts.safetynetalerts.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.ListOfHousehold;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonAddress;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonCount;
import com.safetynet.alerts.safetynetalerts.service.FireStationService;
import com.safetynet.alerts.safetynetalerts.service.ListOfHouseholdService;
import com.safetynet.alerts.safetynetalerts.service.StationNumberService;

@RestController
public class FireStationController {

	@Autowired
	public FireStationService fireStationService;
	@Autowired
	private StationNumberService stationNumberService;
	@Autowired
	private ListOfHouseholdService listOfHouseholdService;

	private static final Log logger = LogFactory.getLog(FireStationController.class);

	@GetMapping(path = "/phoneAlert")
	public List<String> getPhoneNumber(@RequestParam(value = "firestation") int firestation) {
		return fireStationService.getPhoneNumbers(firestation);
	}

	
	/**
	 * This URL should return the fire station number that services the provided address  
	 * as well as a list of all of the people living at the address
	 * @param address
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@GetMapping(path = "/fire")
	public List<PersonAddress> getFireStationNumber(@RequestParam(value = "address") String address)
			throws UnsupportedEncodingException {
		return fireStationService.getFireStationNumber(address);
	}

	/**
	 * this method returns house hold information
	 * @param stationNumber
	 * @return
	 */
	@GetMapping(path = "/firestation")
	public PersonCount getPeopleList(@RequestParam(value = "stationNumber") int stationNumber) {
		return stationNumberService.getPeopleList(stationNumber);
	}

	/**
	 * This should return a list of all the households in each fire station’s jurisdiction. This list needs to group 
	 * people by household address, include name, phone number, and age of each person, and  any 
	 * medications (with dosages) and allergies beside each person’s name.  
	 * @param stations
	 * @return
	 */
	@GetMapping(path = "/flood/stations")
	public List<ListOfHousehold> getHouseholdList(@RequestParam List<Integer> stations) {
		return listOfHouseholdService.getPersonInfo(stations);
	}

	//this method returns all fireStations data
	@GetMapping(path = "/allstations")
	public List<FireStation> getAllFirestation() {
		return fireStationService.getAllFirestation();

	}

	// this method adds a fireStation record
	@PostMapping("/firestation/address")
	public void addFirestationAddress(@RequestBody FireStation fireStation) {
		fireStationService.addNewfirestation(fireStation);
	}
	
	@DeleteMapping("/firestation/address")
    public void deleteFireStationAddress(@RequestParam (value = "address") String address) {
		fireStationService.deleteFireStationAddress(address);
    }
	
	@PutMapping("/firestation/address")
	public FireStation updatefirestationRecord(@RequestBody FireStation firestation) {
		return fireStationService.updateFireStationNum(firestation);
		
	}

}
