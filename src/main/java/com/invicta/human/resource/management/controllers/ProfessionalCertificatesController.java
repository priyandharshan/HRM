package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.ProfessionalCertificatesDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.ProfessionalCertificates;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.ProfessionalCertificatesService;
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
public class ProfessionalCertificatesController {

  Logger log = LoggerFactory.getLogger(ProfessionalCertificatesController.class);

  @Autowired
  private ProfessionalCertificatesService professionalCertificatesService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  @PostMapping(EndPointURI.PROFESSIONAL_CERTIFICATES)
  public ResponseEntity<Object> addProfessionalCertificatesDetails(
      @RequestBody ProfessionalCertificatesDto professionalCertificatesDto) {
    if (!employeeService.existsByEmployeeId(professionalCertificatesDto.getEmployeeId())) {
      log.error(Constants.EMPLOYEE_NOT_FOUND + ":" + professionalCertificatesDto.getEmployeeId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (professionalCertificatesService.existsBycertificateNameAndEmployeeId(
        professionalCertificatesDto.getCertificateName(),
        professionalCertificatesDto.getEmployeeId())) {
      log.error(
          Constants.PROFESSIONAL_CERTIFICATES_ALREADY_EXISTS + ":" + professionalCertificatesDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROFESSIONAL_CERTIFICATES_ALREADY_EXISTS,
              validationFailureResponseCode.getProfessionalCertificatesAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    Employee employee = new Employee();
    employee.setId(professionalCertificatesDto.getEmployeeId());
    ProfessionalCertificates professionalCertificates = new ProfessionalCertificates();
    BeanUtils.copyProperties(professionalCertificatesDto, professionalCertificates);
    professionalCertificates.setEmployee(employee);
    professionalCertificatesService.saveProfessionalCertificatesDetails(professionalCertificates);
    log.info("Professional Certificates");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_PROFESSIONAL_CERTIFICATES),
        HttpStatus.OK);
  }

  @PutMapping(EndPointURI.PROFESSIONAL_CERTIFICATES)
  public ResponseEntity<Object> updateProfessionalCertificatesDetails(
      @RequestBody ProfessionalCertificatesDto professionalCertificatesDto) {
    if (!professionalCertificatesService.existsById(professionalCertificatesDto.getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND,
              validationFailureResponseCode.getProfessionalCertificatesNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    if (!professionalCertificatesService.existsByEmployeeId(
        professionalCertificatesDto.getEmployeeId())) {
      log.error(
          Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND_INEMPLOYEE + professionalCertificatesDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoProfessionalCertificatesInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    if (professionalCertificatesService.updateExistsBycertificateName(
        professionalCertificatesDto.getCertificateName(),
        professionalCertificatesDto.getEmployeeId(), professionalCertificatesDto.getId())) {
      log.error(
          Constants.PROFESSIONAL_CERTIFICATES_ALREADY_EXISTS + ":" + professionalCertificatesDto.getEmployeeId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROFESSIONAL_CERTIFICATES_ALREADY_EXISTS,
              validationFailureResponseCode.getProfessionalCertificatesAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }

    Employee employee = new Employee();
    employee.setId(professionalCertificatesDto.getEmployeeId());
    ProfessionalCertificates professionalCertificates = new ProfessionalCertificates();
    BeanUtils.copyProperties(professionalCertificatesDto, professionalCertificates);
    professionalCertificates.setEmployee(employee);
    professionalCertificatesService.updateProfessionalCertificates(professionalCertificates);
    log.info("Professional Certificates Details Updated");
    return new ResponseEntity<>(new BasicResponse(RestApiResponseStatus.OK,
        Constants.UPDATE_PROFESSIONAL_CERTIFICATES_SUCCESS), HttpStatus.OK);
  }

  @DeleteMapping(EndPointURI.PROFESSIONAL_CERTIFICATES_BY_ID)
  public ResponseEntity<Object> deleteProfessionalCertificateDetails(@PathVariable long id) {
    if (!professionalCertificatesService.existsById(id)) {
      log.error(Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND,
              validationFailureResponseCode.getProfessionalCertificatesNotFound()),
          HttpStatus.BAD_REQUEST);
    }
    professionalCertificatesService.deleteProfessionalCertificateDetails(id);
    log.info("Professional Certificate Details Deleted");
    return new ResponseEntity<>(new BasicResponse(RestApiResponseStatus.OK,
        Constants.DELETE_PROFESSIONAL_CERTIFICATES_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(EndPointURI.PROFESSIONAL_CERTIFICATES_BY_EMPLOYEE_ID)
  public ResponseEntity<Object> getAllProfessionalCertificateDetailsByEmployeeId(
      @PathVariable(value = "employeeId") String employeeId) {
    if (!professionalCertificatesService.existsByEmployeeId(employeeId)) {
      log.error(Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND_INEMPLOYEE);
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PROFESSIONAL_CERTIFICATES_NOT_FOUND_INEMPLOYEE,
              validationFailureResponseCode.getNoProfessionalCertificatesInEmployee()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        professionalCertificatesService.getAllProfessionalCertificateDetailsByEmployeeId(
            employeeId));
  }

}
