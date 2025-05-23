package com.safetynet.alerts.safetynetalerts.test.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.controller.FireStationController;
import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonAddress;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.FireStationService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {
	
 static ObjectMapper mapper = new ObjectMapper();
	 
	 @MockitoBean
//	 @Mock
	 private DataRepository dataService;
	 
	 @InjectMocks
	 FireStationService fireStationService;
	 
//	@Autowired
//	private MockMvc mockMvc;
	 
	 private FireStation fireStation;
	 private FireStation fireStation1;
	 private FireStation fireStation2;
	 private Person person;
	 private MedicalRecord mRecord;

	@BeforeEach
	void setUp() throws Exception {
		
		fireStation = new FireStation("123 Main St", 1);
		fireStation1 = new FireStation("834 Binoc Ave", 3);
		fireStation2 = new FireStation("123 Morrisville", 1);
		
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
	void testGetPhoneNumbers() {
		
		given(dataService.getFirestations())
        .willReturn(List.of(fireStation));
		
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		
		List<String> test= fireStationService.getPhoneNumbers(1);
		System.out.println( test);
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void testGetFireStationNumber() {
		
		given(dataService.getFirestations())
        .willReturn(List.of(fireStation));
		
		given(dataService.getPersons())
        .willReturn(List.of(person));
		
		given(dataService.getMedicalrecords())
        .willReturn(List.of(mRecord));
		
		
		List<PersonAddress> test = null;
		try {
			test = fireStationService.getFireStationNumber("123 Main St");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( test);
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void testGetAllFirestation() {
		given(dataService.getFirestations())
        .willReturn(List.of(fireStation,fireStation1));
		
		List<FireStation> test= fireStationService.getAllFirestation();
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(2);
		
	}

	@Test
	void testAddNewfirestation() {
//		given(dataService.getFirestations()).
//        .willReturn(List.of(fireStation,fireStation1));
//		fireStation2 = new FireStation("123 Morrisville", 1);
		
	/*	given(dataService.getFirestations().add(fireStation2));
		
		
		fireStationService.addNewfirestation( fireStation2 );*/
		
//		assertThat(test).isNotNull();
//        assertThat(test.size()).isEqualTo(3);
	}

	@Test
	void testDeleteFireStationAddress() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdateFireStationNum() {
//		fail("Not yet implemented");
	}

}
