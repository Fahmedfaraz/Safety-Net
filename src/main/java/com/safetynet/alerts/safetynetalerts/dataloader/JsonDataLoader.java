package com.safetynet.alerts.safetynetalerts.dataloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.service.DataRepository;


@Component
public class JsonDataLoader implements CommandLineRunner {
	private static final Log logger = LogFactory.getLog(JsonDataLoader.class);

	@Autowired
	private DataRepository dataService;
	

	@Override
    public void run(String... args) throws Exception {
        loadJsonData();
    }

    private void loadJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
//      Load the JSON file
		InputStream inputStream = getClass().getResourceAsStream("/data.json");
 
		JsonNode rootNode = objectMapper.readTree(inputStream);

		List<Person> personList = objectMapper.convertValue(rootNode.path("persons"), new TypeReference<List<Person>>() {});
		dataService.setPersons(personList);
		logger.info("Person JSON data loaded.");
	
		List<FireStation> firestationList = objectMapper.convertValue(rootNode.path("firestations"), new TypeReference<List<FireStation>>() {});
		dataService.setFirestations(firestationList);
		logger.info("FireStation JSON data loaded.");  	
	   
		List<MedicalRecord> medicalrecordList = objectMapper.convertValue(rootNode.path("medicalrecords"), new TypeReference<List<MedicalRecord>>() {});
		dataService.setMedicalrecords(medicalrecordList);
		logger.info("MedicalRecord JSON data loaded.");
    }
}
