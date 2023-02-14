package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.LaptopAllocationDetailsDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.LaptopAllocationDetails;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.LaptopAllocationDetailsService;
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
public class LaptopAllocationDetailsController {

  Logger log = LoggerFactory.getLogger(LaptopAllocationDetailsController.class);

  @Autowired
  private LaptopAllocationDetailsService laptopAllocationDetailsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.LAPTOP_ALLOCATION)
  public ResponseEntity<Object> addLaptopAllocation(
      @RequestBody LaptopAllocationDetailsDto laptopAllocationDetailsDto) {
    if (!employeeService.existsByEmployeeId(laptopAllocationDetailsDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + laptopAllocationDetailsDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (laptopAllocationDetailsService.isSerialNumberExists(
        laptopAllocationDetailsDto.getSerialNumber())) {
      log.error(
          Constants.LAPTOP_SERIAL_NO_ALREADY_EXISTS + laptopAllocationDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.LAPTOP_SERIAL_NO_ALREADY_EXISTS,
              validationFailureResponseCode.getLaptopSerialNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(laptopAllocationDetailsDto.getEmployeeId());
    LaptopAllocationDetails laptopAllocationDetails = new LaptopAllocationDetails();
    BeanUtils.copyProperties(laptopAllocationDetailsDto, laptopAllocationDetails);
    laptopAllocationDetails.setEmployee(employee);
    laptopAllocationDetailsService.saveLaptopAllocationDetails(laptopAllocationDetails);
    log.info("Added Laptop Allocation");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_LAPTOP_ALLOCATION),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.LAPTOP_ALLOCATION)
  public ResponseEntity<Object> updateLaptopAllocation(
      @RequestBody LaptopAllocationDetailsDto laptopAllocationDetailsDto) {
    if (!laptopAllocationDetailsService.existsById(laptopAllocationDetailsDto.getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.LAPTOP_ALLOCATION_NOT_FOUND,
              validationFailureResponseCode.getLaptopAllocationNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!laptopAllocationDetailsService.existsByEmployeeId(
        laptopAllocationDetailsDto.getEmployeeId())) {
      log.error(
          Constants.LAPTOP_ALLOCATION_NOT_FOUND_INEMPLOYEE + laptopAllocationDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.LAPTOP_ALLOCATION_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoLaptopAllocationInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    if (laptopAllocationDetailsService.isUpdateSerialNumberExists(
        laptopAllocationDetailsDto.getSerialNumber(), laptopAllocationDetailsDto.getEmployeeId())) {
      log.error(
          Constants.LAPTOP_SERIAL_NO_ALREADY_EXISTS + laptopAllocationDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.LAPTOP_SERIAL_NO_ALREADY_EXISTS,
              validationFailureResponseCode.getLaptopSerialNumberAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(laptopAllocationDetailsDto.getEmployeeId());
    LaptopAllocationDetails laptopAllocationDetails = new LaptopAllocationDetails();
    BeanUtils.copyProperties(laptopAllocationDetailsDto, laptopAllocationDetails);
    laptopAllocationDetails.setEmployee(employee);
    laptopAllocationDetails.setId(
        laptopAllocationDetailsService.getLaptopAllocationDetaisByEmployeeId(
            laptopAllocationDetailsDto.getEmployeeId()).getId());
    laptopAllocationDetailsService.updateLaptopAllocationDetails(laptopAllocationDetails);
    log.info("Updated Laptop Allocation");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_LAPTOP_ALLOCATION_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.LAPTOP_ALLOCATION_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllLaptopAllocationByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!laptopAllocationDetailsService.existsByEmployeeId(employeeId)) {
      log.error(Constants.LAPTOP_ALLOCATION_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.LAPTOP_ALLOCATION_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoLaptopAllocationInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        laptopAllocationDetailsService.getAllLaptopAllocationDetailsByEmployeeId(employeeId));
  }
}
