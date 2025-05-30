package com.safetynet.alerts.safetynetalerts.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.controller.CommunityEmailController;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
@SpringBootTest
@AutoConfigureMockMvc
class CommunityEmailControllerTest {
	static ObjectMapper mapper = new ObjectMapper();
	 
	 @MockitoBean
	 private DataRepository dataService;
	 @MockitoBean
	 private CommunityEmailController communityEmailController;
	 @Autowired
		private MockMvc mockMvc;
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetCommunityEmail() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().is2xxSuccessful());
	}

}
