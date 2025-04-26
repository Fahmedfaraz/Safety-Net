package com.safetynet.alerts.safetynetalerts.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.safetynetalerts.rest.model.ChildAlert;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;

@Service
public class ChildAlertService {
	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(ChildAlertService.class);

	/*
	 * public List<ChildAlert> getChildAlert(String address) throws
	 * UnsupportedEncodingException { address = URLDecoder.decode(address, "UTF-8");
	 * List<ChildAlert> childlist = new ArrayList<ChildAlert>(); PersonService ps =
	 * new PersonService();
	 * 
	 * for(Person person:dataService.getPersons()) {
	 * if(person.getAddress().equals(address)) {
	 * person.setFirstName(person.getFirstName());
	 * person.setLastName(person.getLastName());
	 * 
	 * for(MedicalRecord mRecord : dataService.getMedicalrecords()) {
	 * 
	 * if(mRecord.getFirstName().equals(person.getFirstName())&
	 * mRecord.getLastName().equals(person.getLastName())){ ChildAlert cAlert=new
	 * ChildAlert(); if(Integer.parseInt(ps.getAge(mRecord.getBirthdate()))< 18 ) {
	 * 
	 * cAlert.setFirstName(mRecord.getFirstName());
	 * cAlert.setLastName(mRecord.getLastName());
	 * cAlert.setAge(ps.getAge(mRecord.getBirthdate())); } childlist.add(cAlert); }
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * return childlist;
	 * 
	 * }
	 */

	public List<ChildAlert> getChildAlert(String address) throws UnsupportedEncodingException {
		address = URLDecoder.decode(address, "UTF-8");
		List<ChildAlert> childlist = new ArrayList<ChildAlert>();
		PersonService ps = new PersonService();

		int count = 0;
		for (Person person : dataService.getPersons()) {
			if (person.getAddress().equals(address)) {
				ChildAlert cAlert = new ChildAlert();
				cAlert.setFirstName(person.getFirstName());
				cAlert.setLastName(person.getLastName());
				childlist.add(cAlert);
			}
		}
		for (MedicalRecord mRecord : dataService.getMedicalrecords()) {
			for (ChildAlert cl : childlist) {
				if (mRecord.getFirstName().equals(cl.getFirstName())
						&& mRecord.getLastName().equals(cl.getLastName())) {
					if (Integer.parseInt(ps.getAge(mRecord.getBirthdate())) < 18) {
						count++;
					}
					cl.setAge(ps.getAge(mRecord.getBirthdate()));
				}
			}
		}
		if (count > 0) {
			return childlist;
		} else {
			childlist = new ArrayList<ChildAlert>();
			return childlist;
		}

	}

}