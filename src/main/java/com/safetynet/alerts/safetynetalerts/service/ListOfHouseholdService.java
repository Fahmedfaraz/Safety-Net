package com.safetynet.alerts.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.safetynetalerts.rest.model.FireStation;
import com.safetynet.alerts.safetynetalerts.rest.model.ListOfHousehold;
import com.safetynet.alerts.safetynetalerts.rest.model.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.rest.model.MemberOfHousehold;
import com.safetynet.alerts.safetynetalerts.rest.model.Person;
import com.safetynet.alerts.safetynetalerts.rest.model.PersonInfo;

@Service
public class ListOfHouseholdService {

	@Autowired
	public DataRepository dataService;
	private static final Log logger = LogFactory.getLog(FireStationService.class);

	public List<ListOfHousehold> getPersonInfo(List<Integer> stationNumbers) {

		PersonService ps = new PersonService();
		List<ListOfHousehold> allHHbyStationList = new ArrayList<ListOfHousehold>();
		for (int i : stationNumbers) {
			
			for (FireStation fireStation : dataService.getFirestations()) {
				if (fireStation.getStation() == i) {
					ListOfHousehold household = new ListOfHousehold();
					household.setAddress(fireStation.getAddress());
					List<MemberOfHousehold> listMembersHH = new ArrayList<MemberOfHousehold>();
					for (Person person : dataService.getPersons()) {				
						if (person.getAddress().equals(household.getAddress())) {
							MemberOfHousehold personInHousehold = new MemberOfHousehold();
							personInHousehold.setFirstName(person.getFirstName());
							personInHousehold.setLastName(person.getLastName());
							personInHousehold.setPhone(person.getPhone());
							for (MedicalRecord mRecord : dataService.getMedicalrecords()) {
								if (mRecord.getFirstName().equals(personInHousehold.getFirstName())
										& mRecord.getLastName().equals(personInHousehold.getLastName())) {
									personInHousehold.setMedications(mRecord.getMedications());
									personInHousehold.setAllergies(mRecord.getAllergies());
									personInHousehold.setAge(ps.getAge(mRecord.getBirthdate()));
								}
							}
							listMembersHH.add(personInHousehold);
						}					
					}
//					household.setHouseholdMember(mHousehold);
					household.setHouseholdMember(listMembersHH);
					allHHbyStationList.add(household);
				}
			}
		}
		return allHHbyStationList;
	}
}
