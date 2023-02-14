package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.InductionCheckListDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.InductionCheckList;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.InductionCheckListService;
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
public class InductionCheckListController {

  Logger logger = LoggerFactory.getLogger(InductionCheckListController.class);

  @Autowired
  private InductionCheckListService inductionCheckListService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.ADD_INDUCTIONCHECKLIST)
  public ResponseEntity<Object> addInductionCheckList(
      @RequestBody InductionCheckListDto inductionCheckListDto) {
    if (!employeeService.existsByEmployeeId(inductionCheckListDto.getEmployeeId())) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + inductionCheckListDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(inductionCheckListDto.getEmployeeId());
    InductionCheckList inductionCheckList = new InductionCheckList();
    BeanUtils.copyProperties(inductionCheckListDto, inductionCheckList);
    inductionCheckList.setEmployee(employee);
    inductionCheckListService.saveInductionCheckList(inductionCheckList);
    logger.info("Added induction check list");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_INDUCTIONCHECKLIST_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.ADD_INDUCTIONCHECKLIST)
  public ResponseEntity<Object> updateInductionCheckList(
      @RequestBody InductionCheckListDto inductionCheckListDto) {
    if (!inductionCheckListService.existsById(inductionCheckListDto.getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.INDUCTIONCECKLIST_NOT_FOUND,
              validationFailureResponseCode.getInductionCheckListNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    if (!inductionCheckListService.existsByEmployeeId(inductionCheckListDto.getEmployeeId())) {
      logger.error(
          Constants.INDUCTIONCHECKLIST_NOT_FOUND_INEMPLOYEE + inductionCheckListDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.INDUCTIONCHECKLIST_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoInductionCheckListInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(inductionCheckListDto.getEmployeeId());
    InductionCheckList inductionCheckList = new InductionCheckList();
    BeanUtils.copyProperties(inductionCheckListDto, inductionCheckList);
    inductionCheckList.setEmployee(employee);
    inductionCheckListService.updateInductionCheckList(inductionCheckList);
    logger.info("Update induction check list");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_INDUCTIONCHECKLIST_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.INDUCTIONCHECKLIST_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllInductionCheckListDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!inductionCheckListService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.INDUCTIONCHECKLIST_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.INDUCTIONCHECKLIST_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoInductionCheckListInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        inductionCheckListService.getAllInductionCheckListDetailsByEmployeeId(employeeId));
  }

}
