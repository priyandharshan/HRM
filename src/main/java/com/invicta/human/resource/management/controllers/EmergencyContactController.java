package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.EmergencyContactDetailsDto;
import com.invicta.human.resource.management.entities.EmergencyContactDetails;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.ContactDetailsService;
import com.invicta.human.resource.management.services.EmergencyContactDetailsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmergencyContactController {

  @Autowired
  EmergencyContactDetailsService emergencyContactDetailsService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private ContactDetailsService contactDetailsService;

  @PostMapping(EndPointURI.ADD_EMERGENCY_CONTACT_DETAILS)
  public ResponseEntity<Object> createEmergencyContactDetails(
      @RequestBody List<EmergencyContactDetailsDto> emergencyContactDetailsDtoList) {

    for (EmergencyContactDetailsDto emergencyContactDetailsDto : emergencyContactDetailsDtoList) {
      if (emergencyContactDetailsService.existsByPhoneNumberAndEmpId(
          emergencyContactDetailsDto.getPhoneNumber(),
          emergencyContactDetailsDto.getEmployeeId())) {
        return new ResponseEntity<>(new ValidationFailureResponse(
            emergencyContactDetailsDto.getPhoneNumber() + " " + Constants.EMERGENCY_CONTACT_NUMBER_ALREADY_EXISTS,
            validationFailureResponseCode.getEmergencyContactNumberExists()),
            HttpStatus.BAD_REQUEST);
      }
      if (contactDetailsService.getContactByEmployeeId(emergencyContactDetailsDto.getEmployeeId())
          .getMobileNumber().equals(emergencyContactDetailsDto.getPhoneNumber())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.EMERGENCY_CONTACT_NO_ALREADY_EXISTS,
                validationFailureResponseCode.getEmergencyContactAlreadyExistsInContactDetails()),
            HttpStatus.BAD_REQUEST);
      }
    }

    for (EmergencyContactDetailsDto emergencyContactDetailsDto : emergencyContactDetailsDtoList) {
      Employee employee = new Employee();
      employee.setId(emergencyContactDetailsDto.getEmployeeId());
      EmergencyContactDetails emergencyContactDetails = new EmergencyContactDetails();
      BeanUtils.copyProperties(emergencyContactDetailsDto, emergencyContactDetails);
      emergencyContactDetails.setEmployee(employee);
      emergencyContactDetailsService.saveEmergencyContactDetails(emergencyContactDetails);
    }

    return new ResponseEntity<>(new BasicResponse(RestApiResponseStatus.CREATED,
        Constants.EMERGENCY_CONTACT_DETAILS_CREATED), HttpStatus.OK);
  }

  @PutMapping(EndPointURI.UPDATE_EMERGENCY_CONTACT_DETAILS)
  public ResponseEntity<Object> updateEmergencyContactDetails(
      @RequestBody List<EmergencyContactDetailsDto> emergencyContactDetailsDtoList) {

    for (EmergencyContactDetailsDto emergencyContactDetailsDto : emergencyContactDetailsDtoList) {
      if (emergencyContactDetailsService.existsByUpdateEmergencyContact(
          emergencyContactDetailsDto.getPhoneNumber(), emergencyContactDetailsDto.getEmployeeId(),
          emergencyContactDetailsDto.getId())) {
        return new ResponseEntity<>(new ValidationFailureResponse(
            emergencyContactDetailsDto.getPhoneNumber() + " " + Constants.EMERGENCY_CONTACT_NUMBER_ALREADY_EXISTS,
            validationFailureResponseCode.getEmergencyContactNumberExists()),
            HttpStatus.BAD_REQUEST);
      }
      if (contactDetailsService.getContactByEmployeeId(emergencyContactDetailsDto.getEmployeeId())
          .getMobileNumber().equals(emergencyContactDetailsDto.getPhoneNumber())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.EMERGENCY_CONTACT_NO_ALREADY_EXISTS,
                validationFailureResponseCode.getEmergencyContactAlreadyExistsInContactDetails()),
            HttpStatus.BAD_REQUEST);
      }
    }

    for (EmergencyContactDetailsDto emergencyContactDetailsDto : emergencyContactDetailsDtoList) {
      Employee employee = new Employee();
      employee.setId(emergencyContactDetailsDto.getEmployeeId());
      EmergencyContactDetails emergencyContactDetails = new EmergencyContactDetails();
      BeanUtils.copyProperties(emergencyContactDetailsDto, emergencyContactDetails);
      emergencyContactDetails.setEmployee(employee);
      emergencyContactDetailsService.updateEmergencyContactDetails(emergencyContactDetails);
    }

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATED_EMERGENCY_CONTACT_DETAILS));
  }

  @GetMapping(EndPointURI.EMERGENCY_CONTACT_DETAILS)
  public ResponseEntity<Object> getEmergencyContactDetailsByEmployeeId(
      @PathVariable String employeeId) {

    if (!emergencyContactDetailsService.existsByEmployeeId(employeeId)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.ok(
        emergencyContactDetailsService.getEmergencyContactDetailsByEmployeeId(employeeId));
  }

  @DeleteMapping(EndPointURI.EMERGENCY_CONTACT_DETAILS_BY_ID)
  public ResponseEntity<Object> deleteEmergencyContactDetails(@PathVariable Long id) {
    if (!emergencyContactDetailsService.existsById(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.NOT_FOUND,
          validationFailureResponseCode.getEmergencyContactNotFound()), HttpStatus.BAD_REQUEST);
    }
    emergencyContactDetailsService.deleteEmergencyContactDetails(id);

    return ResponseEntity.ok(Constants.DELETED);
  }
}
