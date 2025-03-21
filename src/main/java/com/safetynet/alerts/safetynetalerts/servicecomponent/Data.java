package com.safetynet.alerts.safetynetalerts.servicecomponent;

import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.safetynetalerts.rest.model.Firestation;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;

@Component
public class Data {
private List<Person> person;
private List<Firestation> firestation;
private List<MedicalRecord> medicalrecord;

public List<Person> getPersons() {
	return person;
}
public void setPersons(List<Person> persons) {
	this.person = persons;
}
public List<Firestation> getFirestation() {
	return firestation;
}
public void setFirestation(List<Firestation> firestation) {
	this.firestation = firestation;
}
public List<MedicalRecord> getMedicalrecords() {
	return medicalrecord;
}
public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
	this.medicalrecord = medicalrecords;
}



}
