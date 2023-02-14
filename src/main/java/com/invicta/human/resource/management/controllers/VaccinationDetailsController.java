package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.VaccinationDetailsDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.VaccinationDetailsService;
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
public class VaccinationDetailsController {

  private static final Logger logger = LoggerFactory.getLogger(VaccinationDetailsController.class);
  @Autowired
  private VaccinationDetailsService vaccinationDetailsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.ADD_VACCINATION_DETAILS)
  public ResponseEntity<Object> saveVaccinationDetails(
      @RequestBody VaccinationDetailsDto vaccinationDetailsDto) {
    if (!employeeService.existsByEmployeeId(vaccinationDetailsDto.getEmployeeId())) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + vaccinationDetailsDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    vaccinationDetailsService.saveVaccinationDetails(vaccinationDetailsDto);
    logger.info(Constants.VACCINATION_DETAILS_CREATED);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.VACCINATION_DETAILS_CREATED),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.ADD_VACCINATION_DETAILS)
  public ResponseEntity<Object> updateVaccinationDetails(
      @RequestBody VaccinationDetailsDto vaccinationDetailsDto) {
    if (!vaccinationDetailsService.existsById(vaccinationDetailsDto.getId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.VACCINE_DETAILS_NOT_FOUND,
          validationFailureResponseCode.getVaccineDetailsNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!vaccinationDetailsService.existsByEmployeeId(vaccinationDetailsDto.getEmployeeId())) {
      logger.error(Constants.VACCINATION_DETAILS_NOT_FOUND + vaccinationDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.VACCINATION_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getVaccinationDetailsNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    vaccinationDetailsService.saveVaccinationDetails(vaccinationDetailsDto);
    logger.info(Constants.VACCINATION_DETAILS_UPDATED);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.VACCINATION_DETAILS_UPDATED),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.VACCINATION_DETAILS_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getALLByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!vaccinationDetailsService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.VACCINATION_DETAILS_NOT_FOUND);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.VACCINATION_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getVaccinationDetailsNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        vaccinationDetailsService.getAllVaccinationDetailsByEmployeeId(employeeId));

  }

  @DeleteMapping(EndPointURI.VACCINATION_DETAILS_BY_ID)
  public ResponseEntity<Object> deleteVaccineDetails(@PathVariable(value = "id") long id) {
    if (!vaccinationDetailsService.existsById(id)) {
      logger.error(Constants.VACCINE_DETAILS_NOT_FOUND);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.VACCINE_DETAILS_NOT_FOUND,
          validationFailureResponseCode.getVaccineDetailsNotFound()), HttpStatus.BAD_REQUEST);
    }
    vaccinationDetailsService.deleteVaccinationDetailsById(id);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.VACCINATION_DETAILS_DELETED),
        HttpStatus.OK);
  }

}
