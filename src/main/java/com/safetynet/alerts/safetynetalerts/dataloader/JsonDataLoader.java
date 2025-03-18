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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.repository.PersonsRepository;
import com.safetynet.alerts.safetynetalerts.rest.entity.Persons;

@Component
public class JsonDataLoader implements CommandLineRunner {
	private static final Log logger = LogFactory.getLog(JsonDataLoader.class);

    @Autowired
    private PersonsRepository repository;

    @Override
    public void run(String... args) throws Exception {
        loadJsonData();
    }

    private void loadJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        ClassPathResource resource = new ClassPathResource("persons.json");
        InputStream inputStream = resource.getInputStream();
        List<Persons> entities = objectMapper.readValue(inputStream, new TypeReference<List<Persons>>() {});

        repository.saveAll(entities);
        System.out.println("Persons JSON data loaded into database.");
        logger.trace("Persons JSON data loaded into database.");
    }
}
