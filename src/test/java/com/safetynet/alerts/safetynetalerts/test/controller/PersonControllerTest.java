package com.safetynet.alerts.safetynetalerts.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
import com.safetynet.alerts.safetynetalerts.service.PersonService;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
	static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean
	@MockitoBean
	private PersonService personService;
	
	@MockitoBean
	private DataRepository dataService;

	@Test
	void testGetPersonInfo() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=abc&lastName=xyz"))
		.andExpect(status().is2xxSuccessful());
	}

		@Test
	void testGetchildAlertInfo() throws Exception {
		mockMvc.perform(get("/childAlert?address=951 LoneTree Rd"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testGetAllPersons() throws Exception {
		mockMvc.perform(get("/allPersons"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testGetAllMedications() throws Exception {
		mockMvc.perform(get("/allMedications"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testAddPerson() throws Exception {
		Person person=new Person();
		person.setFirstName("John");
		person.setLastName("XYZ"); 
		person.setAddress("955 E. Rose Dr");
		person.setCity("TestCity");
		person.setZip("97451");
		person.setPhone("141-874-7458");
		person.setEmail("gramps@email.com");	
		
        mockMvc.perform(post("/person")
                .contentType("application/json")
                .content(mapper.writeValueAsString(person)))
                .andExpect(status().is2xxSuccessful());
	}

	@Test
	void testAddmedicalRecord() throws Exception {
		MedicalRecord mRecord= new MedicalRecord();
		mRecord.setFirstName("John");
		mRecord.setLastName("XYZ");
		mRecord.setBirthdate("03/06/1984");
		mRecord.setMedications(List.of("TestMedication2, 200mg"));
		mRecord.setAllergies(List.of("xilliathal"));
		mockMvc.perform(post("/medicalRecord")
                .contentType("application/json")
                .content(mapper.writeValueAsString(mRecord)))
                .andExpect(status().is2xxSuccessful());
	}

	@Test
	void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/deletePerson?firstName=abc&lastName=xyz"))
		.andExpect(status().is2xxSuccessful());
	}
	@Test
	void testDeleteMedicalRecord() throws Exception {
		mockMvc.perform(delete("/deleteMedicalRecord?firstName=abc&lastName=xyz"))
		.andExpect(status().is2xxSuccessful());
	}

}
