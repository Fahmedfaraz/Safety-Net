package com.safetynet.alerts.safetynetalerts.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.model.Firestation;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;

@RestController
public class PersonController {
	private static final Log logger = LogFactory.getLog(PersonController.class);

//	public void loadJsonData() throws IOException {
//		ObjectMapper objectMapper = new ObjectMapper();
////		Load the JSON file
//		InputStream inputStream = getClass().getResourceAsStream("/data.json");
//
////		JsonNode rootNode = objectMapper.readTree(inputStream);		
//	}

//	@GetMapping(path="/person", produces = MediaType.APPLICATION_JSON_VALUE)

	@GetMapping(path = "/person")
	public List<Person> getPerson() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

//		Load the JSON file
		InputStream inputStream = getClass().getResourceAsStream("/data.json");

		JsonNode rootNode = objectMapper.readTree(inputStream);

//		loadJsonData();
		JsonNode personsNode = rootNode.path("persons");

		List<Person> personList = objectMapper.convertValue(personsNode, new TypeReference<List<Person>>() {});

		logger.trace("PersonController has been called");
		return personList;
	}

	@GetMapping(path = "/firestation")
	public List<Firestation> getFirestation() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

//		Load the JSON file
		InputStream inputStream = getClass().getResourceAsStream("/data.json");

		JsonNode rootNode = objectMapper.readTree(inputStream);
		JsonNode firestationsNode = rootNode.path("firestations");

		List<Firestation> firestationList = objectMapper.convertValue(firestationsNode,
				new TypeReference<List<Firestation>>() {
				});

		logger.trace("Firestation Controller has been called");
		return firestationList;
	}

	@GetMapping(path = "/medicalrecord")
	public List<MedicalRecord> getmedicalrecord() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

//		Load the JSON file
		InputStream inputStream = getClass().getResourceAsStream("/data.json");

		JsonNode rootNode = objectMapper.readTree(inputStream);
		JsonNode medicalrecordsNode = rootNode.path("medicalrecords");

		List<MedicalRecord> medicalrecordList = objectMapper.convertValue(medicalrecordsNode,
				new TypeReference<List<MedicalRecord>>() {
				});

		logger.trace("medications Controller has been called");
		return medicalrecordList;
	}
	
	@GetMapping("//communityEmail?city={city}")
	public List<String> getEmailByCity(@RequestParam(required = false) String city) throws IOException {
		 List<String> communityEmail = new ArrayList<>();
		
		ObjectMapper objectMapper = new ObjectMapper();

//		Load the JSON file
		InputStream inputStream = getClass().getResourceAsStream("/data.json");

		JsonNode rootNode = objectMapper.readTree(inputStream);

//		loadJsonData();
		JsonNode cityNode = rootNode.path("persons").path("city");
		JsonNode emailNode = rootNode.path("persons").path("email");


		if (cityNode.equals(city)) {
			communityEmail.add(emailNode.textValue());
        }
		return communityEmail;
}
}