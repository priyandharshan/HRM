package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.EmploymentCategoryDto;
import com.invicta.human.resource.management.entities.EmploymentCategory;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.EmploymentCategoryService;
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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmploymentCategoryController {
  @Autowired
  private EmploymentCategoryService employmentCategoryService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  Logger log = LoggerFactory.getLogger(EmploymentCategoryController.class);

  // Add Employment Category
  @PostMapping(value = EndPointURI.EMPLOYMENTCATEGORY)
  public ResponseEntity<Object> addEmploymentCategory(
      @RequestBody EmploymentCategoryDto employmentCategoryDto) {
    if (employmentCategoryService.isEmploymentCategoryExists(
        employmentCategoryDto.getEmploymentCategory())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYMENT_CATEGORY_ALREADY_EXISTS,
              validationFailureResponseCode.getEmploymentCategoryAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    EmploymentCategory employmentCategory = new EmploymentCategory();
    BeanUtils.copyProperties(employmentCategoryDto, employmentCategory);
    employmentCategoryService.saveEmploymentCategory(employmentCategory);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_EMPLOYMENT_CATEGORY_SUCCESS),
        HttpStatus.OK);
  }

  // Get all Employee Category
  @GetMapping(value = EndPointURI.EMPLOYMENTCATEGORY)
  public ResponseEntity<Object> getAllEmploymentCategory() {
    List<EmploymentCategory> employmentCategory;
    employmentCategory = employmentCategoryService.getAllEmploymentCategory();
    return new ResponseEntity<>(employmentCategory, HttpStatus.OK);
  }

  // Get Employment Category By ID
  @GetMapping(value = EndPointURI.EMPLOYMENTCATEGORYBYID)
  public ResponseEntity<Object> getEmploymentCategoryById(@PathVariable Long id) {
    if (!employmentCategoryService.existsByEmploymentCategoryId(id)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYMENT_CATEGORY_NOT_EXISTS,
              validationFailureResponseCode.getEmploymentCategoryNotexists()),
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(employmentCategoryService.getBYId(id), HttpStatus.OK);

  }

  // Delete Employment Category
  @DeleteMapping(value = EndPointURI.EMPLOYMENTCATEGORYBYID)
  public ResponseEntity<Object> deleteEmploymentCategory(@PathVariable Long id) {
    if (!employmentCategoryService.existsByEmploymentCategoryId(id)) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYMENT_CATEGORY_NOT_EXISTS,
              validationFailureResponseCode.getEmploymentCategoryNotexists()),
          HttpStatus.BAD_REQUEST);
    }
    if (employeeService.existsByEmploymentCategoryId(id)) {
      log.error(Constants.EMPLOYMENT_CATEGORY_DEPEND + id);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYMENT_CATEGORY_DEPEND,
              validationFailureResponseCode.getEmploymentCategoryDepend()), HttpStatus.BAD_REQUEST);
    }
    employmentCategoryService.deleteEmploymentCategory(id);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.DELETE_EMPLOYMENT_CATEGORY_SUCCESS),
        HttpStatus.OK);
  }

  // Update the Employment Category by Employment Category
  @PutMapping(value = EndPointURI.EMPLOYMENTCATEGORY)
  public ResponseEntity<Object> updateEmploymentCategory(
      @RequestBody EmploymentCategoryDto employmentCategoryDto) {
    if (employmentCategoryService.updateEmploymentCategory(
        employmentCategoryDto.getEmploymentCategory(), employmentCategoryDto.getId())) {
      return new ResponseEntity<Object>(
          new ValidationFailureResponse(Constants.EMPLOYMENT_CATEGORY_ALREADY_EXISTS,
              validationFailureResponseCode.getEmploymentCategoryAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    EmploymentCategory employmentCategory = new EmploymentCategory();
    BeanUtils.copyProperties(employmentCategoryDto, employmentCategory);
    employmentCategoryService.updateEmploymentCategory(employmentCategory);
    return new ResponseEntity<Object>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_EMPLOYMENT_CATEGORY_SUCCESS),
        HttpStatus.OK);

  }
}
