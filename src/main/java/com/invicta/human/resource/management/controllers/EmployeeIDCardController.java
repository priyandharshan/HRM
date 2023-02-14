package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.EmployeeIDCardDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.EmployeeIDCard;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeIDCardService;
import com.invicta.human.resource.management.services.EmployeeService;
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
public class EmployeeIDCardController {

  Logger log = LoggerFactory.getLogger(EmployeeIDCardController.class);

  @Autowired
  private EmployeeIDCardService employeeIDCardService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.EMPLOYEE_ID_CARD)
  public ResponseEntity<Object> addEmployeeIdCard(
      @RequestBody EmployeeIDCardDto employeeIDCardDto) {
    if (!employeeService.existsByEmployeeId(employeeIDCardDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + employeeIDCardDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(employeeIDCardDto.getEmployeeId());
    EmployeeIDCard employeeIDCard = new EmployeeIDCard();
    BeanUtils.copyProperties(employeeIDCardDto, employeeIDCard);
    employeeIDCard.setEmployee(employee);
    employeeIDCardService.saveEmployeeIDCard(employeeIDCard);
    log.info("Added Employee ID Card");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_EMPLOYEE_ID_CARD),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.EMPLOYEE_ID_CARD)
  public ResponseEntity<Object> updateEmployeeIdCard(
      @RequestBody EmployeeIDCardDto employeeIDCardDto) {
    if (!employeeIDCardService.existsById(employeeIDCardDto.getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYEE_ID_CARD_NOT_FOUND,
              validationFailureResponseCode.getEmployeeIdCardNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!employeeIDCardService.existsByEmployeeId(employeeIDCardDto.getEmployeeId())) {
      log.error(
          Constants.EMPLOYEE_ID_CARD_NOT_FOUND_INEMPLOYEE + employeeIDCardDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYEE_ID_CARD_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoEmployeeIdCardInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(employeeIDCardDto.getEmployeeId());
    EmployeeIDCard employeeIDCard = new EmployeeIDCard();
    BeanUtils.copyProperties(employeeIDCardDto, employeeIDCard);
    employeeIDCard.setEmployee(employee);
    employeeIDCardService.updateEmployeeIDCard(employeeIDCard);
    log.info("Update Employee ID Card");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.UPDATE_EMPLOYEE_ID_CARD_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.EMPLOYEE_ID_CARD_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllEmployeeIdCardDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!employeeIDCardService.existsByEmployeeId(employeeId)) {
      log.error(Constants.EXIT_PROCEDURE_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYEE_ID_CARD_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoEmployeeIdCardInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        employeeIDCardService.getAllEmployeeIdCardDetailsByEmployeeId(employeeId));
  }

}
