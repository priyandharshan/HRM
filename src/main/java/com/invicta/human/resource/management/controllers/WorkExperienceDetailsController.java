package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.WorkExperienceDetailsDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.WorkExperienceDetailsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class WorkExperienceDetailsController {

  private static final Logger logger =
      LoggerFactory.getLogger(WorkExperienceDetailsController.class);
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private WorkExperienceDetailsService workExperienceDetailsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;

  @PostMapping(EndPointURI.ADD_WORK_EXPERIENCE_DETAILS)
  public ResponseEntity<Object> saveWorkExperienceDetails(
      @RequestBody WorkExperienceDetailsDto workExperienceDetailsDto) {
    if (!employeeService.existsByEmployeeId(workExperienceDetailsDto.getEmployeeId())) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (workExperienceDetailsService.existsByCompanyNameSave(
        workExperienceDetailsDto.getCompanyName(),
        workExperienceDetailsDto.getExperienceDesignation(),
        workExperienceDetailsDto.getEmployeeId())) {
      logger.error(
          Constants.WORK_EXPERIENCE_DETAILS_ALREADY_EXISTS + ":" + workExperienceDetailsDto.getCompanyName());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_ALREADY_EXISTS,
              validationFailureResponseCode.getWorkExperienceDetailsAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    workExperienceDetailsService.saveWorkExperienceDetails(workExperienceDetailsDto);
    return ResponseEntity.ok(new BasicResponse(RestApiResponseStatus.CREATED,
        Constants.WORK_EXPERIENCE_DETAILS_CREATED));
  }

  @PutMapping(EndPointURI.ADD_WORK_EXPERIENCE_DETAILS)
  public ResponseEntity<Object> updateWorkExperienceDetails(
      @RequestBody WorkExperienceDetailsDto workExperienceDetailsDto) {
    if (!workExperienceDetailsService.existsByEmployeeId(
        workExperienceDetailsDto.getEmployeeId())) {
      logger.error(
          Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND_FOR_EMPLOYEE + ":" + workExperienceDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND_FOR_EMPLOYEE,
              validationFailureResponseCode.getWorkExperienceDetailsNotFoundInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    if (!workExperienceDetailsService.existsById(workExperienceDetailsDto.getId())) {
      logger.error(
          Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND + ":" + workExperienceDetailsDto.getId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getWorkExperienceDetailsNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    if (workExperienceDetailsService.existsByCompanyNameUpdate(
        workExperienceDetailsDto.getCompanyName(),
        workExperienceDetailsDto.getExperienceDesignation(),
        workExperienceDetailsDto.getEmployeeId(), workExperienceDetailsDto.getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_ALREADY_EXISTS,
              validationFailureResponseCode.getWorkExperienceDetailsAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    workExperienceDetailsService.saveWorkExperienceDetails(workExperienceDetailsDto);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.WORK_EXPERIENCE_DETAILS_UPDATED));
  }

  @DeleteMapping(EndPointURI.WORK_EXPERIENCE_DETAILS_BY_ID)
  public ResponseEntity<Object> deleteWorkExperienceDetailsById(
      @PathVariable(value = "id") long id) {
    if (!workExperienceDetailsService.existsById(id)) {
      logger.error(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getWorkExperienceDetailsNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    workExperienceDetailsService.deleteWorkExperienceDetailsById(id);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.WORK_EXPERIENCE_DETAILS_DELETED));
  }

  @GetMapping(EndPointURI.WORK_EXPERIENCE_DETAILS_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllWorkExperienceDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!workExperienceDetailsService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND_FOR_EMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND_FOR_EMPLOYEE,
              validationFailureResponseCode.getWorkExperienceDetailsNotFoundInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(workExperienceDetailsService.getAllByEmployeeId(employeeId));
  }

}
