package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.BCardDetailsDto;
import com.invicta.human.resource.management.entities.BCardDetails;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.BCardDetailsService;
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
public class BCardDetailsController {

  Logger log = LoggerFactory.getLogger(BCardDetailsController.class);

  @Autowired
  private BCardDetailsService bCardDetailsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.B_CARD)
  public ResponseEntity<Object> addBCardsDetails(@RequestBody BCardDetailsDto bCardDetailsDto) {
    if (!employeeService.existsByEmployeeId(bCardDetailsDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + bCardDetailsDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (bCardDetailsService.isEpfNumberExists(bCardDetailsDto.getEpfNumber())) {
      log.error(Constants.EPF_NO_ALREADY_EXITS + bCardDetailsDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EPF_NO_ALREADY_EXITS,
          validationFailureResponseCode.getLaptopSerialNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(bCardDetailsDto.getEmployeeId());
    BCardDetails bCardDetails = new BCardDetails();
    BeanUtils.copyProperties(bCardDetailsDto, bCardDetails);
    bCardDetails.setEmployee(employee);
    bCardDetailsService.saveBCardDetails(bCardDetails);
    log.info("B-Card Details Added");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_B_CARD), HttpStatus.OK);
  }

  @PutMapping(EndPointURI.B_CARD)
  public ResponseEntity<Object> updateBCardsDetails(@RequestBody BCardDetailsDto bCardDetailsDto) {
    if (!bCardDetailsService.existsById(bCardDetailsDto.getId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.B_CARD_NOT_FOUND,
          validationFailureResponseCode.getBCardNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!bCardDetailsService.existsByEmployeeId(bCardDetailsDto.getEmployeeId())) {
      log.error(Constants.B_CARD_NOT_FOUND_INEMPLOYEE + bCardDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.B_CARD_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoBCardInEmployee()), HttpStatus.BAD_REQUEST);
    }
    if (bCardDetailsService.isUpdateEpfNumberExists(bCardDetailsDto.getEpfNumber(),
        bCardDetailsDto.getEmployeeId())) {
      log.error(Constants.EPF_NO_ALREADY_EXITS + bCardDetailsDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EPF_NO_ALREADY_EXITS,
          validationFailureResponseCode.getEpfNoAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(bCardDetailsDto.getEmployeeId());
    BCardDetails bCardDetails = new BCardDetails();
    BeanUtils.copyProperties(bCardDetailsDto, bCardDetails);
    bCardDetails.setEmployee(employee);
    bCardDetailsService.updateBCardDetails(bCardDetails);
    log.info("B-Card Details Updated");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_B_CARD_SUCCESS),
        HttpStatus.OK);
  }

  @DeleteMapping(EndPointURI.B_CARD_BY_ID)
  public ResponseEntity<Object> deleteBCardDetails(@PathVariable long id) {
    if (!bCardDetailsService.existsById(id)) {
      log.error(Constants.B_CARD_NOT_FOUND);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.B_CARD_NOT_FOUND,
          validationFailureResponseCode.getBCardNotFound()), HttpStatus.BAD_REQUEST);
    }
    bCardDetailsService.deleteBCardDetails(id);
    log.info("B-Card Details Deleted");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.DELETE_B_CARD_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.B_CARD_DETAILS_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllBCardDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!bCardDetailsService.existsByEmployeeId(employeeId)) {
      log.error(Constants.B_CARD_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.B_CARD_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoBCardInEmployee()), HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(bCardDetailsService.getAllBCardDetailsByEmployeeId(employeeId));
  }

}
