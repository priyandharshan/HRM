package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.ResignationDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.ExitProcedure;
import com.invicta.human.resource.management.entities.Resignation;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.ExitProcedureService;
import com.invicta.human.resource.management.services.ResignationService;
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
public class ResignationController {

  Logger log = LoggerFactory.getLogger(ResignationController.class);

  @Autowired
  private ResignationService resignationService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private ExitProcedureService exitProcedureService;

  @PostMapping(EndPointURI.RESIGNATION)
  public ResponseEntity<Object> addResignation(@RequestBody ResignationDto resignationDto) {
    if (!employeeService.existsByEmployeeId(resignationDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + resignationDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(resignationDto.getEmployeeId());
    Resignation resignation = new Resignation();
    BeanUtils.copyProperties(resignationDto, resignation);
    resignation.setEmployee(employee);
    resignationService.saveResignation(resignation);
    log.info("Resignation Added");

    ExitProcedure exitProcedure = new ExitProcedure();
    exitProcedure.setEmployee(employee);
    exitProcedureService.saveExitProcedure(exitProcedure);
    log.info("Exit procedure added");

    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_RESIGNATION), HttpStatus.OK);
  }

  @PutMapping(EndPointURI.RESIGNATION)
  public ResponseEntity<Object> updateResignation(@RequestBody ResignationDto resignationDto) {
    if (!resignationService.existsById(resignationDto.getId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RESIGNATION_NOT_FOUND,
          validationFailureResponseCode.getResignationNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!resignationService.existsByEmployeeId(resignationDto.getEmployeeId())) {
      log.error(Constants.RESIGNATION_NOT_FOUND_INEMPLOYEE + resignationDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.RESIGNATION_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoResignationInEmployee()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(resignationDto.getEmployeeId());
    Resignation resignation = new Resignation();
    BeanUtils.copyProperties(resignationDto, resignation);
    resignation.setEmployee(employee);
    resignationService.UpdateResignation(resignation);
    log.info("Resignation Updated");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_RESIGNATION_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.RESIGNATION_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllResignationByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!resignationService.existsByEmployeeId(employeeId)) {
      log.error(Constants.RESIGNATION_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.RESIGNATION_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoResignationInEmployee()), HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(resignationService.getAllResignationDetailsByEmployeeId(employeeId));
  }

}
