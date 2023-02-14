package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.EmployeeDto;
import com.invicta.human.resource.management.dto.EmployeeForMainTableDto;
import com.invicta.human.resource.management.dto.LeaveManagementDto;
import com.invicta.human.resource.management.entities.*;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ContentResponse;
import com.invicta.human.resource.management.response.PaginatedContentResponse;
import com.invicta.human.resource.management.response.PaginatedContentResponse.Pagination;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.searchdto.SearchEmployeeDto;
import com.invicta.human.resource.management.services.*;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

  Logger logger = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  DesignationService designationService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  EmploymentCategoryService employmentCategoryService;

  @Autowired
  InductionCheckListService inductionCheckListService;
  @Autowired
  LaptopAllocationDetailsService laptopAllocationDetailsService;
  @Autowired
  KeyRegistryService keyRegistryService;
  @Autowired
  EmployeeIDCardService employeeIDCardService;
  @Autowired
  JobTitleService jobTitleService;
  @Autowired
  UserCredentialsService userCredentialsService;


  @PostMapping(EndPointURI.ADD_EMPLOYEE)
  public ResponseEntity<Object> addEmployee(@RequestBody EmployeeDto employeeDto) {
    if (employeeService.existsByEmployeeId(employeeDto.getId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOEE_ID_ALREADY_EXISTS,
          validationFailureResponseCode.getEmployeeIdAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    if (!designationService.existsByDesignationId(employeeDto.getDesignationId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NOT_EXISTS,
          validationFailureResponseCode.getDesignationNotexists()), HttpStatus.BAD_REQUEST);
    }
    if (!employmentCategoryService.existsByEmploymentCategoryId(
        employeeDto.getEmploymentCategoryId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.EMPLOYMENT_CATEGORY_NOT_EXISTS,
              validationFailureResponseCode.getEmploymentCategoryNotexists()),
          HttpStatus.BAD_REQUEST);
    }
    if (!jobTitleService.existsById(employeeDto.getJobTitleId())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.JOB_TITLE_NOT_FOUND,
          validationFailureResponseCode.getJobTitleIdNotFound()), HttpStatus.BAD_REQUEST);
    }

    Employee employee = new Employee();
    Designation designation = new Designation();
    designation.setId(employeeDto.getDesignationId());
    EmploymentCategory employmentCategory = new EmploymentCategory();
    employmentCategory.setId(employeeDto.getEmploymentCategoryId());
    JobTitle jobTitle = new JobTitle();
    jobTitle.setId(employeeDto.getJobTitleId());

    BeanUtils.copyProperties(employeeDto, employee);
    employee.setDesignation(designation);
    employee.setEmploymentCategory(employmentCategory);
    employee.setJobTitle(jobTitle);
    employeeService.saveEmployee(employee);
    logger.info("Saved employee details");

    InductionCheckList inductionCheckList = new InductionCheckList();
    inductionCheckList.setEmployee(employee);
    inductionCheckListService.saveInductionCheckList(inductionCheckList);
    logger.info("Created induction checklist");

    LaptopAllocationDetails laptopAllocationDetails = new LaptopAllocationDetails();
    laptopAllocationDetails.setEmployee(employee);
    laptopAllocationDetailsService.saveLaptopAllocationDetails(laptopAllocationDetails);
    logger.info("Created laptop allocation details");

    KeyRegistry keyRegistry = new KeyRegistry();
    keyRegistry.setEmployee(employee);
    keyRegistryService.saveKeyRegistry(keyRegistry);
    logger.info("Created key registry");

    EmployeeIDCard employeeIDCard = new EmployeeIDCard();
    employeeIDCard.setEmployee(employee);
    employeeIDCardService.saveEmployeeIDCard(employeeIDCard);
    logger.info("ID card details created");

    //Leave-management-system rest call
    LeaveManagementDto leaveManagementDto=new LeaveManagementDto();
    leaveManagementDto.setEmployeeId(employeeDto.getId());
    leaveManagementDto.setJoinedDate(employeeDto.getJoinedDate());
    employeeService.createLeaveManagement(leaveManagementDto);

    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_EMPLOYEE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(EndPointURI.EMPLOYEES)
  public ResponseEntity<Object> getEmployees() {
    List<EmployeeForMainTableDto> employeeForMainTableDtoList = employeeService.getEmployees();
    return ResponseEntity.ok(employeeForMainTableDtoList);
  }

  @GetMapping(EndPointURI.EMPLOYEE)
  public ResponseEntity<Object> getEmployee(@PathVariable String id) {
    if (!employeeService.existsByEmployeeId(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.ok(employeeService.getProfileDetails(id));
  }

  @PutMapping(EndPointURI.EMPLOYEES)
  public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDto employeeDto) {

    Employee employee = new Employee();
    Designation designation = new Designation();
    designation.setId(employeeDto.getDesignationId());
    EmploymentCategory employmentCategory = new EmploymentCategory();
    employmentCategory.setId(employeeDto.getEmploymentCategoryId());
    JobTitle jobTitle = new JobTitle();
    jobTitle.setId(employeeDto.getJobTitleId());

    if (!employeeService.existsByEmployeeId(employeeDto.getId())) {
      return new ResponseEntity<>(
          new BasicResponse(RestApiResponseStatus.NOT_FOUND, Constants.EMPLOYEE_NOT_FOUND),
          HttpStatus.BAD_REQUEST);
    }

    BeanUtils.copyProperties(employeeDto, employee);
    employee.setDesignation(designation);
    employee.setEmploymentCategory(employmentCategory);
    employee.setJobTitle(jobTitle);
    employeeService.updateEmployee(employee);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_EMPLOYEE_SUCCESS));
  }

  @DeleteMapping(EndPointURI.EMPLOYEE)
  public ResponseEntity<Object> deleteEmployee(@PathVariable String id) {
    if (!employeeService.existsByEmployeeId(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }

    employeeService.deleteEmployee(id);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.DELETE_EMPLOYEE_SUCCESS));
  }

  @GetMapping(EndPointURI.EMPLOYEE_DETAILS_DROP_DOWN)
  public ResponseEntity<Object> getAllEmployeeDetailsDropDown() {
    return ResponseEntity.ok(employeeService.getAllEmployeeDetailsDropDownList());
  }

  @GetMapping(EndPointURI.EMPLOYEE_MAIN)
  public ResponseEntity<Object> getAllMainEmployee() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @GetMapping(EndPointURI.SEARCH_EMPLOYEE)
  public ResponseEntity<ContentResponse<Object>> getAllEmployeeByPagination(
      SearchEmployeeDto searchEmployeeDto, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size, @RequestParam(name = "sortField") String sortField,
      @RequestParam(name = "direction") String direction) {
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sortField);
    Pagination pagination = new Pagination(page, size, 0, 0l);
    List<EmployeeForMainTableDto> employeeSearchList =
        employeeService.searchEmployeePagination(searchEmployeeDto, pageable, pagination);
    return ResponseEntity.ok(
        new PaginatedContentResponse<>("employee", employeeSearchList, RestApiResponseStatus.OK,
            RestApiResponseStatus.OK.getStatus(), pagination));

  }

  @GetMapping(EndPointURI.PROFILE_DETAILS)
  public ResponseEntity<Object> getProfile(Principal principal) {
    String employeeId =
        userCredentialsService.findByUsername(principal.getName()).getEmployee().getId();
    return new ResponseEntity<>(employeeService.getProfileView(employeeId), HttpStatus.OK);
  }
}
