package com.safetynet.alerts.safetynetalerts.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.ListOfHousehold;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.service.CommunityEmailService;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.ListOfHouseholdService;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ListOfHouseholdServiceTest {
	static ObjectMapper mapper = new ObjectMapper();
	@MockitoBean
	 private DataRepository dataService;
	
	@InjectMocks
	ListOfHouseholdService listOfHouseholdService;
	
	private Person person;
	private Person person1;
	private FireStation fireStation;
	private FireStation fireStation1;
	private FireStation fireStation2;
	private MedicalRecord mRecord;
	private MedicalRecord mRecord1;
	
	@BeforeEach
	void setUp() throws Exception {
		fireStation = new FireStation("123 Main St", 1);
		fireStation1 = new FireStation("834 Binoc Ave", 3);

		
		person = new Person();
		person.setFirstName("Brian");
		person.setLastName("Carman");
		person.setAddress("123 Main St");
		person.setPhone("123-133-2345");
		
		person1 = new Person();
		person1.setFirstName("Lily");
		person1.setLastName("Cooper");
		person1.setAddress("834 Binoc Ave");
		person1.setPhone("123-133-2345");
		
		mRecord = new MedicalRecord();
		mRecord.setFirstName("Brian");
		mRecord.setLastName("Carman");
		mRecord.setMedications(List.of("aznol:350mg","hydrapermazol:100mg"));
		mRecord.setAllergies(List.of("nillacilan"));
		mRecord.setBirthdate("03/06/1984");
		
		mRecord1 = new MedicalRecord();
		mRecord1.setFirstName("Lily");
		mRecord1.setLastName("Cooper");
		mRecord1.setMedications(List.of("aznol:350mg","hydrapermazol:100mg"));
		mRecord1.setAllergies(List.of("nillacilan"));
		mRecord1.setBirthdate("09/06/2000");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPersonInfo() {
		given(dataService.getFirestations())
        .willReturn(List.of(fireStation,fireStation1));
		
		given(dataService.getPersons())
        .willReturn(List.of(person,person1));
		
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord,mRecord1));
		
		List<ListOfHousehold> test= listOfHouseholdService.getPersonInfo(List.of(1,3));
		System.out.println( test);
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(2);
		
	}

}
