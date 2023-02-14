package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ContactDetailsDto;
import com.invicta.human.resource.management.entities.ContactDetails;
import com.invicta.human.resource.management.repositories.ContactDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ContactDetailsServiceImplementation implements ContactDetailsService {

  @Autowired
  private ContactDetailsRepository contactDetailsRepository;

  /**
   * Adds contact details into the database
   *
   * @param contactDetails - contact details object
   * @author Dhivyaruban
   * @date 21-10-2022
   */
  @Override
  public void saveContactDetails(ContactDetails contactDetails) {
    contactDetailsRepository.save(contactDetails);
  }

  /**
   * Get all contact details from db and converts into dtos
   *
   * @return List of contact details dto
   * @author Dhivyaruban
   * @date 22-10-2022
   */
  @Override
  public List<ContactDetailsDto> getContactDetailsDtos() {
    List<ContactDetailsDto> contactDetailsDtos = new ArrayList<>();
    List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();

    for (ContactDetails contactDetails : contactDetailsList) {
      ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
      BeanUtils.copyProperties(contactDetails, contactDetailsDto);
      contactDetailsDtos.add(contactDetailsDto);
    }

    return contactDetailsDtos;
  }

  /**
   * Get a contact details and converts to dto
   *
   * @param id - Employee id
   * @return contact details dto
   * @author Dhivyaruban
   * @date 22-10-2022
   */
  @Override
  public ContactDetailsDto getContactDetailsByEmployeeId(String id) {
    ContactDetails contactDetails = contactDetailsRepository.findByEmployeeId(id).get();
    ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
    BeanUtils.copyProperties(contactDetails, contactDetailsDto);
    return contactDetailsDto;
  }

  /**
   * Updates a contact details of an employee
   *
   * @param contactDetails - ContactDetails object
   * @author Dhivyaruban
   * @date 23-10-2022
   */
  @Override
  public void updateContactDetails(ContactDetails contactDetails) {
    contactDetailsRepository.save(contactDetails);
  }

  /**
   * Deletes a contact details
   *
   * @param id - Employee ID
   * @author Dhivyaruban
   * @date 28-10-2022
   */

  @Transactional
  public void deleteContactDetails(String id) {
    contactDetailsRepository.deleteByEmployeeId(id);
  }

  /**
   * Checks whether an email is available
   *
   * @param email - Email of the employee
   * @return - true if email already available
   * @author Dhivyaruban
   * @date 28-10-2022
   */
  @Override
  public boolean existsByEmail(String email) {
    return contactDetailsRepository.existsByEmailIgnoreCase(email);
  }

  /**
   * Checks whether a mobile number is available
   *
   * @param mobileNumber should not be null
   * @return true if mobile number already available
   * @author Dhivyaruban
   * @date 28-10-2022
   */
  @Override
  public boolean existsByMobileNumber(String mobileNumber) {
    return contactDetailsRepository.existsByMobileNumber(mobileNumber);
  }

  /*
   * Checks whether the given email already exists for other employees
   *
   * @return True if email already exists, except the specified employee id
   *
   * @author Dhivyaruban
   *
   * @date 31-10-2022
   */
  @Override
  public boolean existsByUpdateEmail(String email, String id) {
    return contactDetailsRepository.existsByEmailIgnoreCaseAndEmployeeIdIgnoreCaseNot(email, id);
  }

  /*
   * Checks whether another employee has the same mobile number
   *
   * @param mobileNumber should not be null
   *
   * @return true if mobile number already exists for another employee
   *
   * @author Dhivyaruban
   *
   * @date 31-10-2022
   */
  @Override
  public boolean existsByUpdateMobileNumber(String mobileNumber, String id) {
    return contactDetailsRepository.existsByMobileNumberAndEmployeeIdIgnoreCaseNot(mobileNumber,
        id);
  }

  @Override
  public boolean isEmailSame(String email,String employeeId) {
    ContactDetails contactDetails = contactDetailsRepository.findByEmployeeId(employeeId).get();
    return Objects.equals(contactDetails.getEmail(), email);
  }

  /**
   * Returns the employee id of given email
   *
   * @param email Email to find the employee id
   * @return Employee ID of the email
   * @author Dhivyaruban
   * @date 14-11-2022
   */
  @Override
  public String getEmployeeIdByEmail(String email) {
    return contactDetailsRepository.findByEmailIgnoreCase(email).getEmployee().getId();
  }

  /**
   * Returns the employee id of given mobile number
   *
   * @param mobileNumber Mobile number to find the employee id
   * @return Employee ID of the mobile number
   * @author Dhivyaruban
   * @date 14-11-2022
   */
  @Override
  public String getEmployeeIdByMobileNumber(String mobileNumber) {
    return contactDetailsRepository.findByMobileNumber(mobileNumber).getEmployee().getId();
  }

  /**
   * @param employeeId
   * @return
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return contactDetailsRepository.existsByEmployeeIdIgnoreCase(employeeId);
  }

  /**
   * Check the Official Email as Exists or not
   *
   * @param officialEmail
   * @author Thenuga
   * @date 13-12-2022s
   */
  @Override
  public boolean existsByOfficialEmail(String officialEmail) {
    return contactDetailsRepository.existsByOfficialEmailIgnoreCase(officialEmail);
  }

  @Override
  public boolean existsByUpdateOfficialEmail(String officialEmail, String id) {
    return contactDetailsRepository
        .existsByOfficialEmailIgnoreCaseAndEmployeeIdIgnoreCaseNot(officialEmail, id);
  }

  @Override
  public ContactDetails getContactDetailsByEmail(String email) {
    ContactDetails contactDetails=contactDetailsRepository.findByEmailIgnoreCase(email);
    return contactDetails;
  }

  @Override
  public ContactDetails getContactByEmployeeId(String employeeId) {
    ContactDetails contactDetails=contactDetailsRepository.findByEmployeeId(employeeId).get();
    return contactDetails;
  }

}
