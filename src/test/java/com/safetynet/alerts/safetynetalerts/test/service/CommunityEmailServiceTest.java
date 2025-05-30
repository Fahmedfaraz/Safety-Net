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
import com.safetynet.alerts.safetynetalerts.rest.model.ChildAlert;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonAddress;
import com.safetynet.alerts.safetynetalerts.service.CommunityEmailService;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommunityEmailServiceTest {
	static ObjectMapper mapper = new ObjectMapper();
	
	@MockitoBean
	 private DataRepository dataService;
	
	@InjectMocks
	CommunityEmailService communityEmailService;
	
	private Person person;
	private Person person1;
	
	@BeforeEach
	void setUp() throws Exception {
		person = new Person();
		person.setCity("Culver");
		person.setEmail("gramps@email.com");
		
		person1 = new Person();
		person1.setCity("Culver");
		person1.setEmail("clivfd@ymail.com");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetCommunityEmail() {
		given(dataService.getPersons())
        .willReturn(List.of(person,person1));
		
		List<String> test=null;
		try {
			test = communityEmailService.getCommunityEmail("Culver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test);
		
		assertThat(test).isNotNull();
        assertThat(test.size()).isEqualTo(2);
	}

}
