package com.safetynet.alerts.safetynetalerts.dataloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.rest.beans.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.beans.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.beans.Person;


@Component
public class JsonDataLoader implements CommandLineRunner {
	private static final Log logger = LogFactory.getLog(JsonDataLoader.class);

   

    @Override
    public void run(String... args) throws Exception {
        loadJsonData();
    }

    private void loadJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        ClassPathResource resource = new ClassPathResource("data.json");
//        InputStream inputStream = resource.getInputStream();
        
		InputStream inputStream = getClass().getResourceAsStream("/data.json");
            
        
//        Load the JSON file

		JsonNode rootNode = objectMapper.readTree(inputStream);

//		loadJsonData for person node();
		JsonNode personsNode = rootNode.path("persons");
		List<Person> personList = objectMapper.convertValue(personsNode, new TypeReference<List<Person>>() {});
	
		 
		logger.info("Person JSON data loaded.");

	
		List<FireStation> firestationList = objectMapper.convertValue(rootNode.path("firestations"), new TypeReference<List<FireStation>>() {});
		

	      
	   		
	    List<MedicalRecord> medicalrecordList = objectMapper.convertValue(rootNode.path("medicalrecords"), new TypeReference<List<MedicalRecord>>() {});

             
        
//        List<Person> personsList = objectMapper.readValue(inputStream, new TypeReference<List<Person>>() {});

//        repository.saveAll(entities);
       
        
        logger.info("FireStation JSON data loaded.");
    }
}
