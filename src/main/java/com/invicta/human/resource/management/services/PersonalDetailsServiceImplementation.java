package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.PersonalDetailsDto;
import com.invicta.human.resource.management.entities.PersonalDetails;
import com.invicta.human.resource.management.repositories.PersonalDetailsRepository;
import com.invicta.human.resource.management.repositories.UserCredentialsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalDetailsServiceImplementation implements PersonalDetailsService {

  @Autowired
  private PersonalDetailsRepository personalDetailsRepository;
  @Autowired
  private UserCredentialsRepository userCredentialsRepository;

  // Implemented By : Dhivyaruban
  // Implemented On : 21-10-2022
  // Implemented For : Add personal details of an employee
  @Override
  public void savePersonalDetails(PersonalDetails personalDetails) {
    personalDetailsRepository.save(personalDetails);
  }

  // Implemented By : Dhivyaruban
  // Implemented On : 22-10-2022
  // Implemented For : Read personal details of employees
  @Override
  public List<PersonalDetailsDto> getPersonalDetailsDtos() {
    List<PersonalDetailsDto> personalDetailsDtos = new ArrayList<>();
    List<PersonalDetails> personalDetailsList = personalDetailsRepository.findAll();

    for (PersonalDetails personalDetails : personalDetailsList) {
      PersonalDetailsDto personalDetailsDto = new PersonalDetailsDto();
      BeanUtils.copyProperties(personalDetails, personalDetailsDto);
      personalDetailsDtos.add(personalDetailsDto);
    }

    return personalDetailsDtos;
  }

  // Implemented By : Dhivyaruban
  // Implemented On : 22-10-2022
  // Implemented For : Read personal details of an employee
  @Override
  public PersonalDetailsDto getPersonalDetailsByEmployeeId(String id) {
    PersonalDetails personalDetails = personalDetailsRepository.findByEmployeeId(id).get();
    PersonalDetailsDto personalDetailsDto = new PersonalDetailsDto();
    BeanUtils.copyProperties(personalDetails, personalDetailsDto);
    personalDetailsDto.setId(personalDetails.getId());
    return personalDetailsDto;
  }

  // Implemented By : Dhivyaruban
  // Implemented On : 23-10-2022
  // Implemented For : Update personal details of an employee
  @Override
  public void updatePersonalDetails(PersonalDetails personalDetails) {
    personalDetailsRepository.save(personalDetails);
  }

  /**
   * Checks whether a nic number is already exists
   *
   * @param nicNumber should not be null
   * @return true if NIC number already exists in the database
   * @author Dhivyaruban
   * @date 30/10/2022
   */
  @Override
  public boolean existsByNicNumber(String nicNumber) {
    return personalDetailsRepository.existsByNicNumberIgnoreCase(nicNumber);
  }

  /**
   * Checks whether NIC number already available for another employee
   *
   * @param nicNumber should not be null
   * @return true if NIC available for another employee
   * @author Dhivyaruban
   * @date 31-10-2022
   */
  @Override
  public boolean existsByUpdateNicNumber(String nicNumber, String id) {
    return personalDetailsRepository.existsByNicNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(
        nicNumber, id);
  }

  /**
   * Checks whether an employee already exists in persona details repo
   *
   * @param employeeId - ID of the employee
   * @return true if employee id already exists
   * @author Dhivyaruban
   * @date 31-10-2022
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return personalDetailsRepository.existsByEmployeeIdIgnoreCase(employeeId);
  }

  /**
   * Returns the employee with the given NIC number
   *
   * @param nicNumber - NIC number of an employee
   * @return Personal details
   * @author Dhivyaruban
   * @date 14-11-2022
   */
  @Override
  public PersonalDetails getPersonalDetailsByNicNumber(String nicNumber) {
    return personalDetailsRepository.findByNicNumber(nicNumber);
  }

  @Override
  public boolean existsBypassportNumber(String passportNumber) {
    return personalDetailsRepository.existsByPassportNumberIgnoreCase(passportNumber);
  }

  @Override
  public boolean existsByUpdatePasswordNumber(String passportNumber, String employeeId) {
    return personalDetailsRepository.existsByPassportNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(
        passportNumber, employeeId);
  }

  @Override
  public boolean existsByDrivingLicense(String drivingLicenceNumber) {
    return personalDetailsRepository.existsByDrivingLicenseNumberIgnoreCase(drivingLicenceNumber);
  }

  @Override
  public boolean existsByUpdateDrivingLicenseNumber(String drivingLicenseNumber,
      String employeeId) {
    return personalDetailsRepository.existsByDrivingLicenseNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(
        drivingLicenseNumber, employeeId);
  }
}
