package com.safetynet.alerts.safetynetalerts.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonAddress;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.FireStationService;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerTest {
	 static ObjectMapper mapper = new ObjectMapper();
	 
	 @MockitoBean
	 private DataRepository dataService;
	 @MockitoBean
	 private FireStationService firestationService;
	 
	@Autowired
	private MockMvc mockMvc;
	

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPhoneNumber() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testGetFireStationNumber() throws Exception {
//		FireStationController fsControllerMock=mock(FireStationController.class);
//		when(fsControllerMock.getFireStationNumber("951 LoneTree Rd"));
		
//		asserTrue( fsControllerMock.getFireStationNumber("951 LoneTree Rd").size() !=0 , "Station 3 should have persons");
		mockMvc.perform(get("/firestation?stationNumber=1"))
		.andExpect(status().is2xxSuccessful());
	}

	private List<Person> when(List<PersonAddress> fireStationNumber) {
		List<Person> personLst = new ArrayList<Person>();
		Person per = new Person( );
		per.setFirstName("firstName");
		per.setLastName("LastName");
		per.setAddress("951 LoneTree Rd");
		per.setCity("Langhorne");
		per.setEmail("test@test.com");
		per.setZip("19047");
		per.setZip("123-345-5678");
		
		personLst.add(per);
		return personLst;
	}

	@Test
	void testGetPeopleListAtAddress() throws Exception {
		mockMvc.perform(get("/fire?address=951 LoneTree Rd"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testGetHouseholdList() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1,2"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testGetAllFirestation() throws Exception {
		mockMvc.perform(get("/allstations"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testAddFirestation() throws Exception {
		FireStation fireStation=new FireStation();
		fireStation.setAddress("123 Road");
		fireStation.setStation(2); 
        mockMvc.perform(post("/firestation/address")
                .contentType("application/json")
                .content(mapper.writeValueAsString(fireStation)))
                .andExpect(status().is2xxSuccessful());
	}

}
