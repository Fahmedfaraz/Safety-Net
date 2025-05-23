package com.safetynet.alerts.safetynetalerts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonInfo;

@Service
public class PersonService {
	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(PersonService.class);

	public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
		List<PersonInfo> personInfoList = new ArrayList<PersonInfo>();
		for (Person person : dataService.getPersons()) {
			if (person.getFirstName().equalsIgnoreCase(firstName) & person.getLastName().equalsIgnoreCase(lastName)) {
				PersonInfo personInfo = new PersonInfo();
				personInfo.setFirstName(person.getFirstName());
				personInfo.setLastName(person.getLastName());
				personInfo.setAddress(person.getAddress());
				personInfo.setEmail(person.getEmail());

				for (MedicalRecord mRecord : dataService.getMedicalrecords()) {
					if (mRecord.getFirstName().equalsIgnoreCase(firstName)
							& mRecord.getLastName().equalsIgnoreCase(lastName)) {
						personInfo.setMedications(mRecord.getMedications());
						personInfo.setAllergies(mRecord.getAllergies());
						personInfo.setAge(getAge(mRecord.getBirthdate()));
					}
				}
				personInfoList.add(personInfo);

			}

		}
		return personInfoList;

	}

	public String getAge(String birthDateStr) {
		String pattern = "MM/dd/yyyy";
		String age;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
		LocalDate currentDate = LocalDate.now();

		// Calculate the period between the birth date and current date
		Period period = Period.between(birthDate, currentDate);

		// Return the age in years
		age = period.getYears() + "";

		return age;
	}

	public Person addNewPerson(Person person) {
		dataService.getPersons().add(person);
		return person;
	}

	public List<Person> getPersons() {
		return dataService.getPersons();
	}

	public List<MedicalRecord> getAllMedicalRecords() {
		return dataService.getMedicalrecords();
	}

	public void addMedicalRecord(MedicalRecord medRecord) {
		dataService.getMedicalrecords().add(medRecord);
	}

	public void deletePerson(String firstName, String lastName) {
		for (Person person : dataService.getPersons()) {
			if (person.getFirstName().equalsIgnoreCase(firstName) & person.getLastName().equalsIgnoreCase(lastName)) {
				dataService.getPersons().remove(person);
				break;
			}
		}
//		return dataService.getPersons();
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		for (MedicalRecord mRecord : dataService.getMedicalrecords()) {
			if (mRecord.getFirstName().equalsIgnoreCase(firstName) & mRecord.getLastName().equalsIgnoreCase(lastName)) {
				dataService.getMedicalrecords().remove(mRecord);
				break;
			}
		}

	}

	public Person updatePerson(Person updatePerson) {
		for (Person person : dataService.getPersons()) {
			if (updatePerson.getFirstName().equalsIgnoreCase(person.getFirstName())
					& updatePerson.getLastName().equalsIgnoreCase(person.getLastName())) {
				person.setAddress(updatePerson.getAddress());
				person.setCity(updatePerson.getCity());
				person.setZip(updatePerson.getZip());
				person.setPhone(updatePerson.getPhone());
				person.setEmail(updatePerson.getEmail());

				break;
			}
		}
		return updatePerson;
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord updatedMedRecoed) {
		for (MedicalRecord medRecord : dataService.getMedicalrecords()) {
			if (updatedMedRecoed.getFirstName().equalsIgnoreCase(medRecord.getFirstName())
					& updatedMedRecoed.getLastName().equalsIgnoreCase(medRecord.getLastName())) {
				medRecord.setBirthdate(updatedMedRecoed.getBirthdate());
				medRecord.setMedications(updatedMedRecoed.getMedications());
				medRecord.setAllergies(updatedMedRecoed.getAllergies());
				break;
			}
		}
		return updatedMedRecoed;
	}
}
