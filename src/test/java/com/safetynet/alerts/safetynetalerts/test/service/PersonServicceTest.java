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
		
		
		List<PersonInfo> test= personService.getPersonInfo("Brian", "Carman");
		System.out.println( test);
		
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
	}

	@Test
	void testUpdateMedicalRecord() {
//		fail("Not yet implemented");
	}
	
	@Test
	void testGetAge() {
//		fail("Not yet implemented");
	}
}
