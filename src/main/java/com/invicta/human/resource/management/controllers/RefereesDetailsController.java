package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.RefereesDetailsDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.ContactDetailsService;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.RefereesDetailsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
public class RefereesDetailsController {

  private static final Logger logger = LoggerFactory.getLogger(RefereesDetailsController.class);

  @Autowired
  private RefereesDetailsService refereesDetailsService;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private ContactDetailsService contactDetailsService;

  @PostMapping(EndPointURI.ADD_REFEREES_DETAILS)
  public ResponseEntity<Object> saveRefereesDetails(
      @RequestBody List<RefereesDetailsDto> refereesDetailsDtoList) {

    if (refereesDetailsDtoList.size() == 2) {
      if (!(refereesDetailsDtoList.get(0)
          .getRefereeContactNumber() == null && refereesDetailsDtoList.get(1)
          .getRefereeContactNumber() == null)) {
        if (Objects.equals(refereesDetailsDtoList.get(0).getRefereeContactNumber(),
            refereesDetailsDtoList.get(1).getRefereeContactNumber())) {
          return new ResponseEntity<>(new ValidationFailureResponse(Constants.REFEREES_CONTACT_SAME,
              validationFailureResponseCode.getRefereesContactSame()), HttpStatus.BAD_REQUEST);
        }
      }
    }

    for (RefereesDetailsDto refereesDetailsDto : refereesDetailsDtoList) {
      if (!employeeService.existsByEmployeeId(refereesDetailsDto.getEmployeeId())) {
        logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + refereesDetailsDto.getEmployeeId());
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
            validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
      }

      if (refereesDetailsService.existsByPhoneNumberAndEmpId(
          refereesDetailsDto.getRefereeContactNumber(), refereesDetailsDto.getEmployeeId())) {

        logger.error(
            Constants.CONTACT_NO_ALREADY_EXISTS + ":" + refereesDetailsDto.getEmployeeId());

        return new ResponseEntity<>(new ValidationFailureResponse(
            refereesDetailsDto.getRefereeContactNumber() + Constants.REF_NUMBER_EXISTS,
            validationFailureResponseCode.getRefereesContactNoAlreadyExists()),
            HttpStatus.BAD_REQUEST);
      }
      if (contactDetailsService.existsByMobileNumber(refereesDetailsDto.getRefereeContactNumber())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.REFEREES_CONTACT_SAME_AS_CONTACT,
                validationFailureResponseCode.getReferenceContactExistsInContactDetails()),
            HttpStatus.BAD_REQUEST);
      }
    }

    for (RefereesDetailsDto refereesDetailsDto : refereesDetailsDtoList) {
      refereesDetailsService.saveRefereesDetails(refereesDetailsDto);
    }

    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.REFEREES_DETAILS_CREATED),
        HttpStatus.OK);

  }

  @PutMapping(EndPointURI.ADD_REFEREES_DETAILS)
  public ResponseEntity<Object> updateRefereesDetails(
      @RequestBody List<RefereesDetailsDto> refereesDetailsDtoList) {

    for (RefereesDetailsDto refereesDetailsDto : refereesDetailsDtoList) {
      if (!refereesDetailsService.existsByEmployeeId(refereesDetailsDto.getEmployeeId())) {
        logger.error(Constants.REFEREES_DETAILS_NOT_FOUND);
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.REFEREES_DETAILS_NOT_FOUND_FOR_EMPLOYEE,
                validationFailureResponseCode.getRefereesNotFoundForEmployee()),
            HttpStatus.BAD_REQUEST);
      }

      if (!refereesDetailsService.existsById(refereesDetailsDto.getId())) {
        logger.error(Constants.REFEREES_DETAILS_NOT_FOUND);
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.REFEREES_DETAILS_NOT_FOUND,
                validationFailureResponseCode.getRefereesNotFound()), HttpStatus.BAD_REQUEST);
      }

      if (refereesDetailsService.existsByUpdateRefereesContact(
          refereesDetailsDto.getRefereeContactNumber(), refereesDetailsDto.getEmployeeId(),
          refereesDetailsDto.getId())) {

        return new ResponseEntity<>(new ValidationFailureResponse(
            refereesDetailsDto.getRefereeContactNumber() + Constants.REF_NUMBER_EXISTS,
            validationFailureResponseCode.getEmergencyContactNumberExists()),
            HttpStatus.BAD_REQUEST);
      }

      if (contactDetailsService.existsByMobileNumber(refereesDetailsDto.getRefereeContactNumber())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.REFEREES_CONTACT_SAME_AS_CONTACT,
                validationFailureResponseCode.getReferenceContactExistsInContactDetails()),
            HttpStatus.BAD_REQUEST);
      }

    }

    for (RefereesDetailsDto refereesDetailsDto : refereesDetailsDtoList) {
      refereesDetailsService.saveRefereesDetails(refereesDetailsDto);
    }

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.REFEREES_DETAILS_UPDATED));
  }

  @DeleteMapping(EndPointURI.REFEREES_DETAILS_BY_ID)
  public ResponseEntity<Object> deleteRefereesDetailsByEmployeeId(
      @PathVariable(value = "id") long id) {
    if (!refereesDetailsService.existsById(id)) {
      logger.error(Constants.REFEREES_DETAILS_NOT_FOUND);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.REFEREES_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getRefereesNotFound()), HttpStatus.BAD_REQUEST);
    }
    refereesDetailsService.deleteRefereesDetailsById(id);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.REFEREES_DETAILS_DELETED));

  }

  @GetMapping(EndPointURI.REFEREES_DETAILS_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllRefereesDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!refereesDetailsService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.REFEREES_DETAILS_NOT_FOUND);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.REFEREES_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getRefereesNotFound()), HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(refereesDetailsService.getAllRefereesDetailsByEmployeeId(employeeId));
  }

}
