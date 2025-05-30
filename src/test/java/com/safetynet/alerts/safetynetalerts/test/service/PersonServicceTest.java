package com.safetynet.alerts.safetynetalerts.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonInfo;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.PersonService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServicceTest {
	
	static ObjectMapper mapper = new ObjectMapper();
	@Mock
	 private DataRepository dataService;
	 	 
	 @InjectMocks
	 PersonService personService;
	 
	private Person person;
	private MedicalRecord mRecord;

	@BeforeEach
	void setUp() throws Exception {
		person = new Person();
		person.setFirstName("Brian");
		person.setLastName("Carman");
		person.setAddress("123 Main St");
		person.setCity("City");
		person.setEmail("clivfd@ymail.co");
		person.setPhone("123-133-2345");
		
		mRecord = new MedicalRecord();
		mRecord.setFirstName("Brian");
		mRecord.setLastName("Carman");
		mRecord.setMedications(List.of("aznol:350mg","hydrapermazol:100mg"));
		mRecord.setAllergies(List.of("nillacilan"));
		mRecord.setBirthdate("03/06/1984");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPersonInfo() {
		
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord));
		
		
		List<PersonInfo> test= personService.getPersonInfo("Brian", "Carman");
		System.out.println( test);
		test.get(0).getAddress();
		test.get(0).getEmail();
		test.get(0).getFirstName();
		test.get(0).getLastName();
		test.get(0).getAge();
		test.get(0).getMedications();
		test.get(0).getAllergies();
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void testGetPersons() {
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		List<Person> test= personService.getPersons();
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void testGetAllMedicalRecords() {
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord));
		
		List<MedicalRecord> test= personService.getAllMedicalRecords();
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(1);
	}
	
	@Test
	void testAddNewPerson() {
//		fail("Not yet implemented");
	}
	
	@Test
	void testAddMedicalRecord() {
//		fail("Not yet implemented");
	}

	@Test
	void testDeletePerson() {
//		fail("Not yet implemented");
	}

	@Test
	void testDeleteMedicalRecord() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdatePerson() {
//		fail("Not yet implemented");
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		Person updated = new Person();
		
		updated.setFirstName("Brian");
		updated.setLastName("Carman");
		updated.setAddress("123 Main St");
		updated.setCity("Test123");
		updated.setPhone("123-133-2345");
		
		Person test= personService.updatePerson( updated);
		
		
		assertThat(test.getCity()).isEqualTo("Test123");
		
	}

	@Test
	void testUpdateMedicalRecord() {
//		fail("Not yet implemented");
		
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord));
		
		MedicalRecord updatedmRecord = new MedicalRecord();
		updatedmRecord.setFirstName("Brian");
		updatedmRecord.setLastName("Carman");
		updatedmRecord.setMedications(List.of("aznol:350mg","hydrapermazol:100mg"));
		updatedmRecord.setAllergies(List.of("Test123"));
		updatedmRecord.setBirthdate("03/06/1984");
		
		MedicalRecord test= personService.updateMedicalRecord( updatedmRecord);
		
		assertThat(test).isNotNull();
        assertThat(test.getAllergies().get(0)).isEqualTo("Test123");
	}
	
	@Test
	void testGetAge() {
//		fail("Not yet implemented");
	}
}
