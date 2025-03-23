package com.safetynet.alerts.safetynetalerts;

import java.lang.System.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.safetynet.alerts.safetynetalerts.controller.LoggingController;

@SpringBootApplication
public class SafetynetAlertsApplication {
//	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingController.class);
	public static void main(String[] args) {
		SpringApplication.run(SafetynetAlertsApplication.class, args);
//		logger.debug("This is a DEBUG log");
	}

}
