package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.PersonalDetailsDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.PersonalDetails;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.PersonalDetailsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.SearchField;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class PersonalDetailsController {

  private static final Logger logger = LoggerFactory.getLogger(PersonalDetailsController.class);

  @Autowired
  PersonalDetailsService personalDetailsService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;

  @PostMapping(EndPointURI.ADD_PERSONAL_DETAILS)
  public ResponseEntity<Object> createPersonalDetails(
      @RequestBody PersonalDetailsDto personalDetailsDto) {

    if (personalDetailsService.existsByEmployeeId(personalDetailsDto.getEmployeeId())) {
      logger.error(personalDetailsDto.getEmployeeId() + " already exists");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOEE_ID_ALREADY_EXISTS,
          validationFailureResponseCode.getEmployeeIdAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    if (personalDetailsService.existsByNicNumber(personalDetailsDto.getNicNumber())) {
      logger.error(personalDetailsDto.getNicNumber() + " already exists");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.NIC_ALREADY_EXISTS,
          validationFailureResponseCode.getNicAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    if (!SearchField.isNullOrEmpty(
        personalDetailsDto.getPassportNumber()) && personalDetailsService.existsBypassportNumber(
        personalDetailsDto.getPassportNumber())) {
      logger.error(Constants.PASSPORT_NUMBER_ALREADY_EXISTS);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PASSPORT_NUMBER_ALREADY_EXISTS,
              validationFailureResponseCode.getPassportNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    if (!SearchField.isNullOrEmpty(
        personalDetailsDto.getDrivingLicenseNumber()) && personalDetailsService.existsByDrivingLicense(
        personalDetailsDto.getDrivingLicenseNumber())) {
      logger.error(Constants.DRIVING_LICENCE_ALREADY_EXISTS);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.DRIVING_LICENCE_ALREADY_EXISTS,
              validationFailureResponseCode.getDrivingLicenseNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }


    Employee employee = new Employee();
    employee.setId(personalDetailsDto.getEmployeeId());
    PersonalDetails personalDetails = new PersonalDetails();
    BeanUtils.copyProperties(personalDetailsDto, personalDetails);
    personalDetails.setEmployee(employee);
    personalDetailsService.savePersonalDetails(personalDetails);

    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.PERSONAL_DETAILS_CREATED),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.UPDATE_PERSONAL_DETAILS)
  public ResponseEntity<Object> updatePersonalDetails(
      @RequestBody PersonalDetailsDto personalDetailsDto) {

    if (personalDetailsService.existsByUpdateNicNumber(personalDetailsDto.getNicNumber(),
        personalDetailsDto.getEmployeeId())) {
      logger.error(personalDetailsDto.getNicNumber() + " already exists");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.NIC_ALREADY_EXISTS,
          validationFailureResponseCode.getNicAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    if (!SearchField.isNullOrEmpty(
        personalDetailsDto.getPassportNumber()) && personalDetailsService.existsByUpdatePasswordNumber(
        personalDetailsDto.getPassportNumber(), personalDetailsDto.getEmployeeId())) {
      logger.error(Constants.PASSPORT_NUMBER_ALREADY_EXISTS);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PASSPORT_NUMBER_ALREADY_EXISTS,
              validationFailureResponseCode.getPassportNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    if (!SearchField.isNullOrEmpty(
        personalDetailsDto.getDrivingLicenseNumber()) && personalDetailsService.existsByUpdateDrivingLicenseNumber(
        personalDetailsDto.getDrivingLicenseNumber(), personalDetailsDto.getEmployeeId())) {
      logger.error(Constants.DRIVING_LICENCE_ALREADY_EXISTS);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.DRIVING_LICENCE_ALREADY_EXISTS,
              validationFailureResponseCode.getDrivingLicenseNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }

    PersonalDetails personalDetails = new PersonalDetails();
    Employee employee = new Employee();
    employee.setId(personalDetailsDto.getEmployeeId());
    BeanUtils.copyProperties(personalDetailsDto, personalDetails);
    personalDetails.setEmployee(employee);
    personalDetails.setId(
        personalDetailsService.getPersonalDetailsByEmployeeId(personalDetailsDto.getEmployeeId())
            .getId());
    personalDetailsService.updatePersonalDetails(personalDetails);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATED_PERSONAL_DETAILS));
  }

}
