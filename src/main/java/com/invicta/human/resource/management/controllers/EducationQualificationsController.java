package com.invicta.human.resource.management.controllers;


import com.invicta.human.resource.management.dto.EducationQualificationDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EducationQualificationsService;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.InstitutionsService;
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
public class EducationQualificationsController {

  private static final Logger logger =
      LoggerFactory.getLogger(EducationQualificationsController.class);

  @Autowired
  private EducationQualificationsService educationQualificationsService;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private InstitutionsService institutionsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;

  @PostMapping(EndPointURI.ADD_EDUCATION_QUALIFICATION)
  public ResponseEntity<Object> saveEducationQualification(
      @RequestBody EducationQualificationDto educationQualificationDto) {
    if (!employeeService.existsByEmployeeId(educationQualificationDto.getEmployeeId())) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + educationQualificationDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (educationQualificationsService.existsByDegree(educationQualificationDto.getDegree(),
        educationQualificationDto.getInstituteName(), educationQualificationDto.getEmployeeId())) {
      logger.error(
          Constants.DEGREE_ALREADY_EXISTS + ":" + educationQualificationDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.DEGREE_ALREADY_EXISTS,
          validationFailureResponseCode.getDegreeAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    educationQualificationsService.saveEducationQualification(educationQualificationDto);
    return ResponseEntity.ok(new BasicResponse(RestApiResponseStatus.CREATED,
        Constants.EDUCATION_QUALIFICATION_CREATED));
  }

  @PutMapping(EndPointURI.ADD_EDUCATION_QUALIFICATION)
  public ResponseEntity<Object> updateEducationQualification(
      @RequestBody EducationQualificationDto educationQualificationDto) {
    if (!educationQualificationsService.existsByEmployeeId(
        educationQualificationDto.getEmployeeId())) {
      logger.error(
          Constants.EDUCATION_QUALIFICATION_NOT_FOUND_FOR_EMPLOYEE + ":" + educationQualificationDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EDUCATION_QUALIFICATION_NOT_FOUND_FOR_EMPLOYEE,
              validationFailureResponseCode.getEducationQualificationNotFoundInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    if (!educationQualificationsService.existsById(educationQualificationDto.getId())) {
      logger.error(
          Constants.EDUCATION_QUALIFICATION_NOT_FOUND + ":" + educationQualificationDto.getId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EDUCATION_QUALIFICATION_NOT_FOUND,
              validationFailureResponseCode.getEducationQualificationNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    if (educationQualificationsService.existsByUpdateDegree(educationQualificationDto.getDegree(),
        educationQualificationDto.getInstituteName(), educationQualificationDto.getEmployeeId(),
        educationQualificationDto.getId())) {
      logger.error(
          Constants.DEGREE_ALREADY_EXISTS + ":" + educationQualificationDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.DEGREE_ALREADY_EXISTS,
          validationFailureResponseCode.getDegreeAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    educationQualificationsService.updateEducationQualification(educationQualificationDto);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.EDUCATION_QUALIFICATION_UPDATED));

  }

  @DeleteMapping(EndPointURI.EDUCATION_QUALIFICATION_BY_ID)
  public ResponseEntity<Object> deleteEducationQualification(@PathVariable(value = "id") long id) {
    if (!educationQualificationsService.existsById(id)) {
      logger.error(Constants.EDUCATION_QUALIFICATION_NOT_FOUND + ":" + id);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EDUCATION_QUALIFICATION_NOT_FOUND,
              validationFailureResponseCode.getEducationQualificationNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    educationQualificationsService.deleteEducationQualification(id);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.EDUCATION_QUALIFICATION_DELETED));
  }

  @GetMapping(EndPointURI.EDUCATION_QUALIFICATION_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllEducationQualificationByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!educationQualificationsService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.EDUCATION_QUALIFICATION_NOT_FOUND_FOR_EMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EDUCATION_QUALIFICATION_NOT_FOUND_FOR_EMPLOYEE,
              validationFailureResponseCode.getEducationQualificationNotFoundInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        educationQualificationsService.getAllEducationQualificationByEmployeeId(employeeId));
  }

  @GetMapping(EndPointURI.GET_ALL_INSTITUTES)
  public ResponseEntity<Object> getAllInstitutes() {
    return ResponseEntity.ok(institutionsService.getAllInstitutions());
  }

}
