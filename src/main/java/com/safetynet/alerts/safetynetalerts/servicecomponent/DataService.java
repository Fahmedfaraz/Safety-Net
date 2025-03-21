package com.safetynet.alerts.safetynetalerts.servicecomponent;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataService {
private Data data;

public DataService() {
//	ObjectMapper objectMapper = new ObjectMapper();
//	
//	try {
//		data=objectMapper.readValue(new File("C:/Users/faiza/eclipse-workspace/safetynet-alerts/src/main/resources/data.json"), Data.class);
//	}  catch (IOException e) {
//		e.printStackTrace();
//		
//	}
}

public Data getData() {
	return data;
}
}
