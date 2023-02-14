package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.ContactDetailsDto;
import com.invicta.human.resource.management.entities.ContactDetails;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.*;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
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
public class ContactDetailsController {

  private static final Logger logger = LoggerFactory.getLogger(ContactDetailsController.class);
  @Autowired
  ContactDetailsService contactDetailsService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  UserCredentialsService userCredentialsService;
  @Autowired
  EmailService emailService;
  @Autowired
  EmployeeService employeeService;
  @Autowired
  EmergencyContactDetailsService emergencyContactDetailsService;

  @PostMapping(EndPointURI.ADD_CONTACT_DETAILS)
  public ResponseEntity<Object> createContactDetails(
      @RequestBody ContactDetailsDto contactDetailsDto) {

    if (contactDetailsService.existsByEmployeeId(contactDetailsDto.getEmployeeId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOEE_ID_ALREADY_EXISTS,
          validationFailureResponseCode.getEmployeeIdAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    if (contactDetailsService.existsByEmail(contactDetailsDto.getEmail())) {
      logger.error(
          contactDetailsDto.getEmail() + " already exists for employee ID: " + contactDetailsService.getEmployeeIdByEmail(
              contactDetailsDto.getEmail()));
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_ALREADY_EXISTS,
          validationFailureResponseCode.getEmailAlreadyExits()), HttpStatus.BAD_REQUEST);
    }
    if (contactDetailsDto.getOfficialEmail() != null && contactDetailsService.existsByOfficialEmail(
        contactDetailsDto.getOfficialEmail())) {
      logger.error(
          contactDetailsDto.getOfficialEmail() + " already exists for employee ID: " + contactDetailsDto.getOfficialEmail());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.OFFICAL_EMAIL_ALREADY_EXISTS,
              validationFailureResponseCode.getOfficialEmailAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    if (!employeeService.existsByEmployeeId(contactDetailsDto.getEmployeeId())) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + contactDetailsDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (contactDetailsService.existsByMobileNumber(contactDetailsDto.getMobileNumber())) {
      logger.error(
          contactDetailsDto.getMobileNumber() + " already exists for employee ID: " + contactDetailsService.getEmployeeIdByMobileNumber(
              contactDetailsDto.getMobileNumber()));

      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MOBILE_NUMBER_ALREADY_EXISTS,
              validationFailureResponseCode.getMobileAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    if (emergencyContactDetailsService.existsByPhoneNumberAndEmployeeId(
        contactDetailsDto.getMobileNumber(), contactDetailsDto.getEmployeeId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMERGENCY_CONTACT_NO_ALREADY_EXISTS,
              validationFailureResponseCode.getContactNoExistsInEmergencyContactDetails()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(contactDetailsDto.getEmployeeId());

    ContactDetails contactDetails = new ContactDetails();
    BeanUtils.copyProperties(contactDetailsDto, contactDetails);
    contactDetails.setEmployee(employee);
    contactDetailsService.saveContactDetails(contactDetails);
    userCredentialsService.saveUserCredentials(contactDetails);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.CONTACT_DETAILS_CREATED),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.UPDATE_CONTACT_DETAILS)
  public ResponseEntity<Object> updateContactDetails(
      @RequestBody ContactDetailsDto contactDetailsDto) {

    if (!employeeService.existsByEmployeeId(contactDetailsDto.getEmployeeId())) {
      return new ResponseEntity<>(
          new BasicResponse(RestApiResponseStatus.NOT_FOUND, Constants.EMPLOYEE_NOT_FOUND),
          HttpStatus.BAD_REQUEST);
    }

    if (contactDetailsDto.getOfficialEmail() != null && contactDetailsService.existsByUpdateEmail(
        contactDetailsDto.getEmail(), contactDetailsDto.getEmployeeId())) {
      logger.error(
          contactDetailsDto.getEmail() + " already exists for employee ID: " + contactDetailsService.getEmployeeIdByEmail(
              contactDetailsDto.getEmail()));
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_ALREADY_EXISTS,
          validationFailureResponseCode.getEmailAlreadyExits()), HttpStatus.BAD_REQUEST);
    }
    if (contactDetailsDto.getOfficialEmail() != null && contactDetailsService.existsByUpdateOfficialEmail(
        contactDetailsDto.getOfficialEmail(), contactDetailsDto.getEmployeeId())) {
      logger.error(
          contactDetailsDto.getEmail() + " already exists for employee ID: " + contactDetailsDto.getOfficialEmail());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.OFFICAL_EMAIL_ALREADY_EXISTS,
              validationFailureResponseCode.getOfficialEmailAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }

    if (contactDetailsService.existsByUpdateMobileNumber(contactDetailsDto.getMobileNumber(),
        contactDetailsDto.getEmployeeId())) {
      logger.error(
          contactDetailsDto.getMobileNumber() + " already exists for employee ID: " + contactDetailsService.getEmployeeIdByMobileNumber(
              contactDetailsDto.getMobileNumber()));
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.MOBILE_NUMBER_ALREADY_EXISTS,
              validationFailureResponseCode.getMobileAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    if (emergencyContactDetailsService.existsByPhoneNumberAndEmployeeId(
        contactDetailsDto.getMobileNumber(), contactDetailsDto.getEmployeeId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMERGENCY_CONTACT_NO_ALREADY_EXISTS,
              validationFailureResponseCode.getContactNoExistsInEmergencyContactDetails()),
          HttpStatus.BAD_REQUEST);
    }
    ContactDetails contactDetails =
        contactDetailsService.getContactByEmployeeId(contactDetailsDto.getEmployeeId());
    if (!contactDetailsService.isEmailSame(contactDetailsDto.getEmail(),
        contactDetailsDto.getEmployeeId())) {
      userCredentialsService.updateUserCredentials(contactDetailsDto);
    }
    BeanUtils.copyProperties(contactDetailsDto, contactDetails);
    contactDetailsService.updateContactDetails(contactDetails);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATED_CONTACT_DETAILS));
  }

  @GetMapping(value = EndPointURI.USER_ACTIVE_STATUS)
  public ResponseEntity<Object> activeUserStatus(@RequestBody @PathVariable String id) {
    if (!userCredentialsService.existsByEmployeeId(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    userCredentialsService.deactivateUserCredentials(id);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATED_USER_DEACTIVATE_STATUS));

  }
}
