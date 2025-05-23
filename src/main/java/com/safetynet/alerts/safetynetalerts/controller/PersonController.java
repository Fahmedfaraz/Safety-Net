package com.safetynet.alerts.safetynetalerts.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.rest.model.ChildAlert;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonInfo;
import com.safetynet.alerts.safetynetalerts.service.ChildAlertService;
import com.safetynet.alerts.safetynetalerts.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	public PersonService personService;
	@Autowired
	public ChildAlertService childAlertService;
	private static final Log logger = LogFactory.getLog(PersonController.class);
		
	@GetMapping("/personInfo")
	public List<PersonInfo> getPersonInfo(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName)  {
		return personService.getPersonInfo(firstName,lastName);
				 
	}
	
	@GetMapping("/childAlert")
	public List<ChildAlert> getchildAlertInfo(@RequestParam(value = "address") String address) throws UnsupportedEncodingException  {
		return childAlertService.getChildAlert(address);
				 
	}
	
	@GetMapping("/allPersons")
	public List<Person> getAllPersons()  {
		return personService.getPersons();			 
	}
	
	@GetMapping("/allMedications")
	public List<MedicalRecord> getAllMedications()  {
		return personService.getAllMedicalRecords();			 
	}
	
	@PostMapping("/person")
	public void addPerson(@RequestBody Person person) {
		personService.addNewPerson(person);
	}
	
	@PostMapping("/medicalRecord")
	public void addmedicalRecord(@RequestBody MedicalRecord medRecord) {
		personService.addMedicalRecord(medRecord);
	}
	
	@DeleteMapping("/deletePerson")
    public void deletePerson(@RequestParam (value = "firstName") String firstName, @RequestParam (value = "lastName") String lastName) {
		personService.deletePerson(firstName,lastName);
    }
	@DeleteMapping("/deleteMedicalRecord")
    public void deleteMedicalRecord(@RequestParam (value = "firstName") String firstName, @RequestParam (value = "lastName") String lastName) {
		personService.deleteMedicalRecord(firstName,lastName);
    }
	
	@PutMapping("/person")
	public Person updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person);
		
	}
	@PutMapping("/medicalRecord")
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord mRecoed) {
		return personService.updateMedicalRecord(mRecoed);
		
	}

}
