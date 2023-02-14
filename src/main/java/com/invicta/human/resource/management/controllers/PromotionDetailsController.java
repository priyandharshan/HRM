package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.PromotionDetailsDto;
import com.invicta.human.resource.management.entities.Designation;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.PromotionDetails;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmailService;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.PromotionDetailsService;
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
public class PromotionDetailsController {

  private static final Logger logger = LoggerFactory.getLogger(PromotionDetailsController.class);
  @Autowired
  PromotionDetailsService promotionDetailsService;
  @Autowired
  EmployeeService employeeService;
  @Autowired
  private EmailService emailService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;

  @PostMapping(EndPointURI.ADD_PROMOTION_DETAILS)
  public ResponseEntity<Object> createPromotionDetails(
      @RequestBody PromotionDetailsDto promotionDetailsDto) {
    if (promotionDetailsService.existsByPromotionDesignation(promotionDetailsDto.getDesignationId(),
        promotionDetailsDto.getEmployeeId())) {
      logger.error(
          Constants.PROMOTION_DETAILS_ALREADY_EXISTS + ":" + promotionDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROMOTION_DETAILS_ALREADY_EXISTS,
              validationFailureResponseCode.getPromotionDetailsAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    Designation promotedDesignation = new Designation();
    promotedDesignation.setId(promotionDetailsDto.getDesignationId());
    Employee employee = employeeService.getEmployeeDetails(promotionDetailsDto.getEmployeeId());

    employee.setDesignation(promotedDesignation);

    PromotionDetails promotionDetails = new PromotionDetails();
    BeanUtils.copyProperties(promotionDetailsDto, promotionDetails);
    promotionDetails.setEmployee(employee);
    promotionDetails.setPromotedDesignation(promotedDesignation);
    emailService.sendPromotionEmail(promotionDetails);
    promotionDetailsService.savePromotionDetails(promotionDetails);
    employeeService.updateEmployee(employee);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.PROMOTION_DETAILS_CREATED));
  }

  @PutMapping(EndPointURI.PROMOTION_DETAILS_LIST)
  public ResponseEntity<Object> updatePromotionDetails(
      @RequestBody PromotionDetailsDto promotionDetailsDto) {
    if (promotionDetailsService.existsByUpdatePromotionDesignation(
        promotionDetailsDto.getDesignationId(), promotionDetailsDto.getEmployeeId(),
        promotionDetailsDto.getId())) {
      logger.error(
          Constants.PROMOTION_DETAILS_ALREADY_EXISTS + ":" + promotionDetailsDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROMOTION_DETAILS_ALREADY_EXISTS,
              validationFailureResponseCode.getPromotionDetailsAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }

    Employee employee = employeeService.getEmployeeDetails(promotionDetailsDto.getEmployeeId());
    Designation promotedDesignation = new Designation();
    promotedDesignation.setId(promotionDetailsDto.getDesignationId());

    PromotionDetails promotionDetails = new PromotionDetails();
    BeanUtils.copyProperties(promotionDetailsDto, promotionDetails);
    promotionDetails.setEmployee(employee);
    promotionDetails.setPromotedDesignation(promotedDesignation);
    emailService.sendPromotionEmail(promotionDetails);
    promotionDetailsService.savePromotionDetails(promotionDetails);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATED_PROMOTION_DETAILS));
  }

  @DeleteMapping(EndPointURI.PROMOTION_DETAILS)
  public ResponseEntity<Object> deletePromotionDetails(@PathVariable long id) {
    promotionDetailsService.deletePromotionDetails(id);
    return ResponseEntity.ok(Constants.DELETED_PROMOTION_DETAILS);
  }
}
