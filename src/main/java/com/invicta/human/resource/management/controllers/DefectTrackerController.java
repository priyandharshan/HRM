package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.DefectTrackerEmployeeDto;
import com.invicta.human.resource.management.dto.UserCredentialsDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.ContactDetailsService;
import com.invicta.human.resource.management.services.DefectTrackerService;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.UserCredentialsService;
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

@CrossOrigin(origins = "*")
@RestController
public class DefectTrackerController {

  private static final Logger logger = LoggerFactory.getLogger(DefectTrackerController.class);

  @Autowired
  private DefectTrackerService defectTrackerService;
  @Autowired
  private ContactDetailsService contactDetailsService;
  @Autowired
  private UserCredentialsService userCredentialsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.VERIFY)
  public ResponseEntity<Object> VerifyEmail(@RequestBody UserCredentialsDto userCredentialsDto) {
    if (!contactDetailsService.existsByEmail(userCredentialsDto.getUsername())) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + userCredentialsDto.getUsername());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (!userCredentialsService.isActive(userCredentialsDto)) {
      String empId = defectTrackerService.Verify(userCredentialsDto);
      if (empId != null) {
        if (contactDetailsService.getContactDetailsByEmail(userCredentialsDto.getUsername())
            .getEmployee().isDefectTrackerSystemUser()) {
          defectTrackerService.defectTrackerDto(empId);
        }
        return new ResponseEntity<>(
            new BasicResponse(RestApiResponseStatus.OK, Constants.VERIFY_SUCCESS), HttpStatus.OK);
      } else {
        logger.error(Constants.VERIFY_FAILED);
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.VERIFY_FAILED,
            validationFailureResponseCode.getEmailVerifiedFailed()), HttpStatus.BAD_REQUEST);
      }
    } else {
      logger.error(Constants.EMAIL_ALREADY_VERIFIED);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_ALREADY_VERIFIED,
          validationFailureResponseCode.getEmailAlreadyVerified()), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(EndPointURI.DEFECT_TRACKER_SYSTEM)
  public ResponseEntity<Object> sendListToDefectTrackerSystem(
      @RequestBody List<DefectTrackerEmployeeDto> employeeIdList) {
    defectTrackerService.sendAsListToDefectTrackerSystem(employeeIdList);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.DEFECT_TRACKER_SYSTEM));
  }

}
