package com.safetynet.alerts.safetynetalerts.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.safetynetalerts.service.CommunityEmailService;

@RestController
public class CommunityEmailController {
	@Autowired
	public CommunityEmailService communityEmailService;
	
	private static final Log logger = LogFactory.getLog(CommunityEmailController.class);

	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam(value = "city") String city) {
		return communityEmailService.getCommunityEmail(city);
				 
	}
}
