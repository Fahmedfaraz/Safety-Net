package com.safetynet.alerts.safetynetalerts.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.io.UnsupportedEncodingException;
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
import com.safetynet.alerts.safetynetalerts.rest.model.ChildAlert;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.service.ChildAlertService;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.FireStationService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ChildAlertServiceTest {
	static ObjectMapper mapper = new ObjectMapper();
	 
	 @MockitoBean
	 private DataRepository dataService;
	 
	 @InjectMocks
	 ChildAlertService childAlertService;
	 
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
		mRecord.setBirthdate("03/06/2015");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetChildAlert() throws UnsupportedEncodingException {
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord));
		
		
		List<ChildAlert> test= childAlertService.getChildAlert("123 Main St");
		System.out.println( test);
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(1);
	}

}
