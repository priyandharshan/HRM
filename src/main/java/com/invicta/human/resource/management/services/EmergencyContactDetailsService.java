package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EmergencyContactDetailsDto;
import com.invicta.human.resource.management.dto.EmergencyContactDetailsIdDto;
import com.invicta.human.resource.management.entities.EmergencyContactDetails;

import java.util.List;

public interface EmergencyContactDetailsService {

  void saveEmergencyContactDetails(EmergencyContactDetails emergencyContactDetails);

  List<EmergencyContactDetailsDto> getEmergencyContactDetailsDtos();

  List<EmergencyContactDetailsIdDto> getEmergencyContactDetailsByEmployeeId(String id);

  void updateEmergencyContactDetails(EmergencyContactDetails emergencyContactDetails);

  boolean existsByEmployeeId(String employeeId);

  boolean existsByMobileNumber(String mobileNumber);

  boolean existsByPhoneNumberAndEmpId(String phoneNumber, String employeeId);

  boolean existsByUpdateEmergencyContact(String phoneNumber, String employeeId, long id);

  void deleteEmergencyContactDetails(Long id);

  boolean existsById(Long id);

  boolean existsByPhoneNumberAndEmployeeId(String phoneNumber, String employeeId);
}
