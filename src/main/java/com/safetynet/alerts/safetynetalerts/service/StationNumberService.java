package com.safetynet.alerts.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonCount;
import com.safetynet.alerts.safetynetalerts.rest.model.StationNumber;

@Service
public class StationNumberService {
	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(ChildAlertService.class);
	
	public PersonCount getPeopleList(int stationNumber) {
		List<StationNumber> stationlist = new ArrayList<StationNumber>();
		List<String> address = new ArrayList<>();
		PersonService ps=new PersonService();
		int childCount=0;
		int adultCount=0;
		for(FireStation fStation: dataService.getFirestations()) {
			if(fStation.getStation()==stationNumber) {
//				StationNumber stationNum = new StationNumber();
//				stationNum.setAddress(fStation.getAddress());
//				stationlist.add(stationNum);
				address.add(fStation.getAddress());
			}
		}
		for(Person person : dataService.getPersons()) {
			if(address.contains(person.getAddress())){
				StationNumber stationNum = new StationNumber();
				stationNum.setAddress(person.getAddress());
				stationNum.setFirstName(person.getFirstName());
				stationNum.setLastName(person.getLastName());
				stationNum.setPhone(person.getPhone());
				for(MedicalRecord mRecord: dataService.getMedicalrecords()) {
					if(mRecord.getFirstName().equals(person.getFirstName()) & mRecord.getLastName().equals(person.getLastName())){
						stationNum.setAge(ps.getAge(mRecord.getBirthdate()));
						if(Integer.parseInt(stationNum.getAge())< 18) {
							childCount++;
						}
						else {
							adultCount++;
						}
						break;
						
					}
				}
			stationlist.add(stationNum)	;
			
			}
			
		}
			PersonCount pc=new PersonCount();
			pc.setAdultCount("" +adultCount);
			pc.setChildCount("" +childCount);
			pc.setPersonList(stationlist);
			return pc ;
		
		
	}

}
