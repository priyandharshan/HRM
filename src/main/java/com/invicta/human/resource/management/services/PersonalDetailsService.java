package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.PersonalDetailsDto;
import com.invicta.human.resource.management.entities.PersonalDetails;

import java.util.List;

public interface PersonalDetailsService {

  void savePersonalDetails(PersonalDetails personalDetails);

  List<PersonalDetailsDto> getPersonalDetailsDtos();

  PersonalDetailsDto getPersonalDetailsByEmployeeId(String id);

  void updatePersonalDetails(PersonalDetails personalDetails);

  boolean existsByNicNumber(String nicNumber);

  boolean existsByUpdateNicNumber(String nicNumber, String id);

  boolean existsByEmployeeId(String employeeId);

  PersonalDetails getPersonalDetailsByNicNumber(String nicNumber);

  boolean existsBypassportNumber(String passportNumber);

  boolean existsByUpdatePasswordNumber(String passportNumber, String employeeId);

  boolean existsByDrivingLicense(String drivingLicenceNumber);

  boolean existsByUpdateDrivingLicenseNumber(String drivingLicenseNumber, String employeeId);
}
