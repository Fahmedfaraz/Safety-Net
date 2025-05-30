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
import com.safetynet.alerts.safetynetalerts.rest.model.PersonCount;
import com.safetynet.alerts.safetynetalerts.rest.model.StationNumber;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.StationNumberService;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StationNumberServiceTest {
	static ObjectMapper mapper = new ObjectMapper();
	@MockitoBean
	 private DataRepository dataService;
	
	@InjectMocks
	StationNumberService stationNumberService;
	
	private Person person;
	private FireStation fireStation;
	private MedicalRecord mRecord;
	@BeforeEach
	void setUp() throws Exception {
		fireStation = new FireStation("834 Binoc Ave", 3);
		
		person = new Person();
		person.setFirstName("Lily");
		person.setLastName("Cooper");
		person.setAddress("834 Binoc Ave");
		person.setPhone("123-133-2345");
		
		mRecord = new MedicalRecord();
		mRecord.setFirstName("Lily");
		mRecord.setLastName("Cooper");
//		mRecord.setMedications(List.of("aznol:350mg","hydrapermazol:100mg"));
//		mRecord.setAllergies(List.of("nillacilan"));
		mRecord.setBirthdate("09/06/2000");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPeopleList() {
		given(dataService.getFirestations())
        .willReturn(List.of(fireStation));
		
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord));
		
//		List<StationNumber> test= stationNumberService.getPeopleList(3));
		PersonCount test= stationNumberService.getPeopleList(3);
		System.out.println( test);
		
		assertThat(test).isNotNull();
        assertThat(test.getAdultCount()).isEqualTo("1");
//	    assertThat(test.size()).isEqualTo(1);
	}

}
