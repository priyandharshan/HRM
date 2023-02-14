package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.KeyRegistryDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.KeyRegistry;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.KeyRegistryService;
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
public class KeyRegistryController {

  Logger log = LoggerFactory.getLogger(KeyRegistryController.class);

  @Autowired
  private KeyRegistryService keyRegistryService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.KEY_REGISTRY)
  public ResponseEntity<Object> addKeyRegistry(@RequestBody KeyRegistryDto keyRegistryDto) {
    if (!employeeService.existsByEmployeeId(keyRegistryDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + keyRegistryDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (keyRegistryService.isKeyNumberExists(keyRegistryDto.getKeyNumber())) {
      log.error(Constants.KEY_NUMBER_ALREADY_EXISTS + keyRegistryDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.KEY_NUMBER_ALREADY_EXISTS,
          validationFailureResponseCode.getKeyNumberAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(keyRegistryDto.getEmployeeId());
    KeyRegistry keyRegistry = new KeyRegistry();
    BeanUtils.copyProperties(keyRegistryDto, keyRegistry);
    keyRegistry.setEmployee(employee);
    keyRegistryService.saveKeyRegistry(keyRegistry);
    log.info("Key Registry Added");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_KEY_REGISTRY),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.KEY_REGISTRY)
  public ResponseEntity<Object> updateKeyRegistry(@RequestBody KeyRegistryDto keyRegistryDto) {
    if (!keyRegistryService.existsById(keyRegistryDto.getId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.KEY_REGISTRY_NOT_FOUND,
          validationFailureResponseCode.getKeyRegistryNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (!keyRegistryService.existsByEmployeeId(keyRegistryDto.getEmployeeId())) {
      log.error(Constants.KEY_REGISTRY_NOT_FOUND_INEMPLOYEE + keyRegistryDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.KEY_REGISTRY_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoKeyRegistryInEmployee()), HttpStatus.BAD_REQUEST);
    }
    if (keyRegistryService.isUpdateKeyNumberExists(keyRegistryDto.getKeyNumber(),
        keyRegistryDto.getEmployeeId())) {
      log.error(Constants.KEY_NUMBER_ALREADY_EXISTS + keyRegistryDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.KEY_NUMBER_ALREADY_EXISTS,
          validationFailureResponseCode.getKeyNumberAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(keyRegistryDto.getEmployeeId());
    KeyRegistry keyRegistry = new KeyRegistry();
    BeanUtils.copyProperties(keyRegistryDto, keyRegistry);
    if (!keyRegistryDto.isKeyAvailable()) {
      keyRegistry.setKeyNumber(null);
    }
    keyRegistry.setEmployee(employee);
    keyRegistryService.updateKeyRegistry(keyRegistry);
    log.info("Key Registry Updated");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_KEY_REGISTRY_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.KEY_REGISTRY_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllKeyRegistryDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!keyRegistryService.existsByEmployeeId(employeeId)) {
      log.error(Constants.KEY_REGISTRY_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.KEY_REGISTRY_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoKeyRegistryInEmployee()), HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(keyRegistryService.getAllKeyRegistryDetailsByEmployeeId(employeeId));
  }
}
