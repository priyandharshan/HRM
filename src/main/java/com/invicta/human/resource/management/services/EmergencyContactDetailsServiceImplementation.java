package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EmergencyContactDetailsDto;
import com.invicta.human.resource.management.dto.EmergencyContactDetailsIdDto;
import com.invicta.human.resource.management.entities.EmergencyContactDetails;
import com.invicta.human.resource.management.repositories.EmergencyContactDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyContactDetailsServiceImplementation
    implements EmergencyContactDetailsService {

  @Autowired
  private EmergencyContactDetailsRepository emergencyContactDetailsRepository;

  /**
   * Adds emergency contact details of an employee into the database using
   * emergencyContactDetailsRepository
   *
   * @param emergencyContactDetails - EmergencyContactDetails object
   * @author Dhivyaruban
   * @date 21-10-2022
   * @update 27-10-2022
   */
  @Override
  public void saveEmergencyContactDetails(EmergencyContactDetails emergencyContactDetails) {
    emergencyContactDetailsRepository.save(emergencyContactDetails);
  }

  /**
   * Get all emergency contact details
   *
   * @return list of all emergency contact details that exists in the database
   * @author Dhivyaruban
   * @date 22-10-2022
   */
  @Override
  public List<EmergencyContactDetailsDto> getEmergencyContactDetailsDtos() {
    List<EmergencyContactDetailsDto> emergencyContactDetailsDtos = new ArrayList<>();
    List<EmergencyContactDetails> emergencyContactDetailsList =
        emergencyContactDetailsRepository.findAll();

    for (EmergencyContactDetails emergencyContactDetails : emergencyContactDetailsList) {
      EmergencyContactDetailsDto emergencyContactDetailsDto = new EmergencyContactDetailsDto();
      BeanUtils.copyProperties(emergencyContactDetails, emergencyContactDetailsDto);
      emergencyContactDetailsDtos.add(emergencyContactDetailsDto);
    }

    return emergencyContactDetailsDtos;
  }

  /**
   * Get emergency contact detail of an employee
   *
   * @param id contact details id
   * @return emergency contact details dto
   * @author Dhivyaruban
   * @date 22-10-2022
   * @update date 27-10-2022
   */
  @Override
  public List<EmergencyContactDetailsIdDto> getEmergencyContactDetailsByEmployeeId(String id) {
    return emergencyContactDetailsRepository.findByEmployeeId(id);
  }

  /**
   * Updates emergency contact details of an employee
   *
   * @param emergencyContactDetails - EmergencyContactDetails object
   * @author Dhivyaruban
   * @date 23-10-2022
   */
  @Override
  public void updateEmergencyContactDetails(EmergencyContactDetails emergencyContactDetails) {
    emergencyContactDetailsRepository.save(emergencyContactDetails);
  }

  /**
   * @param employeeId
   * @return
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return emergencyContactDetailsRepository.existsByEmployeeIdIgnoreCase(employeeId);
  }

  /**
   * Check mobile number exists or not
   *
   * @param mobileNumber
   * @author Thenuga
   * @date 30-11-2022
   */
  @Override
  public boolean existsByMobileNumber(String mobileNumber) {
    return emergencyContactDetailsRepository.existsByPhoneNumber(mobileNumber);
  }

  /**
   * @param phoneNumber Emergency contact numbers
   * @param employeeId Employee id
   * @return true if mobile number already exists for given employee id
   */
  @Override
  public boolean existsByPhoneNumberAndEmpId(String phoneNumber, String employeeId) {
    return emergencyContactDetailsRepository.existsByPhoneNumberAndEmployeeId(phoneNumber,
        employeeId);
  }

  /**
   * @param phoneNumber Emergency contact number
   * @param employeeId Employee ID
   * @param id Primary key of Emergency contact
   * @return true if phoneNumber already exists for given employeeId
   */
  @Override
  public boolean existsByUpdateEmergencyContact(String phoneNumber, String employeeId, long id) {
    return emergencyContactDetailsRepository.existsByPhoneNumberAndEmployeeIdAndIdNot(phoneNumber,
        employeeId, id);
  }

  /**
   * @param id Emergency contact id
   */
  @Override
  public void deleteEmergencyContactDetails(Long id) {
    emergencyContactDetailsRepository.deleteById(id);
  }

  /**
   * @param id Emergency contact id
   * @return true if emergency contact number exists
   */
  @Override
  public boolean existsById(Long id) {
    return emergencyContactDetailsRepository.existsById(id);
  }

  @Override
  public boolean existsByPhoneNumberAndEmployeeId(String phoneNumber, String employeeId) {
    return emergencyContactDetailsRepository.existsByPhoneNumberAndEmployeeId(phoneNumber,
        employeeId);
  }
}
