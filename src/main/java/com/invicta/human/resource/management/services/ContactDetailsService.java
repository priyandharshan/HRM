package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ContactDetailsDto;
import com.invicta.human.resource.management.entities.ContactDetails;

import java.util.List;

public interface ContactDetailsService {

  void saveContactDetails(ContactDetails contactDetails);

  List<ContactDetailsDto> getContactDetailsDtos();

  ContactDetailsDto getContactDetailsByEmployeeId(String id);

  void updateContactDetails(ContactDetails contactDetails);

  void deleteContactDetails(String id);

  boolean existsByEmail(String email);

  boolean existsByMobileNumber(String mobileNumber);

  boolean existsByUpdateEmail(String email, String id);

  boolean existsByUpdateMobileNumber(String mobileNumber, String id);

  boolean isEmailSame(String email, String employeeId);

  String getEmployeeIdByEmail(String email);

  String getEmployeeIdByMobileNumber(String mobileNumber);

  boolean existsByEmployeeId(String employeeId);

  boolean existsByOfficialEmail(String officialEmail);

  boolean existsByUpdateOfficialEmail(String officialEmail, String id);

  ContactDetails getContactDetailsByEmail(String email);

  ContactDetails getContactByEmployeeId(String employeeId);

}
