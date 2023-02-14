package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.ExitProcedureDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.ExitProcedure;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.ExitProcedureService;
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
public class ExitProcedureController {

  Logger log = LoggerFactory.getLogger(ExitProcedureController.class);

  @Autowired
  private ExitProcedureService exitProcedureService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.ADD_EXIT_PROCEDURE)
  public ResponseEntity<Object> addExitProcedure(@RequestBody ExitProcedureDto exitProcedureDto) {
    if (!employeeService.existsByEmployeeId(exitProcedureDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + exitProcedureDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(exitProcedureDto.getEmployeeId());
    ExitProcedure exitProcedure = new ExitProcedure();
    BeanUtils.copyProperties(exitProcedureDto, exitProcedure);
    exitProcedure.setEmployee(employee);
    exitProcedureService.saveExitProcedure(exitProcedure);
    log.info("Exit Procedure Added");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_EXIT_PROCEDURE),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.ADD_EXIT_PROCEDURE)
  public ResponseEntity<Object> updateExitProcedure(
      @RequestBody ExitProcedureDto exitProcedureDto) {
    if (!exitProcedureService.existsById(exitProcedureDto.getId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EXIT_PROCEDURE_NOT_FOUND,
          validationFailureResponseCode.getExitProcedureNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!exitProcedureService.existsByEmployeeId(exitProcedureDto.getEmployeeId())) {
      log.error(Constants.EXIT_PROCEDURE_NOT_FOUND_INEMPLOYEE + exitProcedureDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EXIT_PROCEDURE_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoExitProcedureInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(exitProcedureDto.getEmployeeId());
    ExitProcedure exitProcedure = new ExitProcedure();
    BeanUtils.copyProperties(exitProcedureDto, exitProcedure);
    exitProcedure.setEmployee(employee);
    exitProcedureService.updateExitProcedure(exitProcedure);
    log.info("Exit Procedure Updated");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_EXIT_PROCEDURE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.EXIT_PROCEDURE_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllExitProcedureDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!exitProcedureService.existsByEmployeeId(employeeId)) {
      log.error(Constants.EXIT_PROCEDURE_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EXIT_PROCEDURE_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoExitProcedureInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        exitProcedureService.getAllExitProcedureDetailsByEmployeeId(employeeId));
  }

}
