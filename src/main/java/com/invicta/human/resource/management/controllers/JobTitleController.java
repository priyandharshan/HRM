package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.JobTitleDto;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.services.JobTitleService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class JobTitleController {

  Logger logger = LoggerFactory.getLogger(JobTitleController.class);

  @Autowired
  JobTitleService jobTitleService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  EmployeeService employeeService;

  @PostMapping(EndPointURI.JOB_TITLE)
  public ResponseEntity<Object> createJobTitle(@Valid @RequestBody JobTitleDto jobTitleDto) {
    if (jobTitleService.existsByJobTitle(jobTitleDto.getJobTitle())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(jobTitleDto.getJobTitle() + Constants.ALREADY_EXISTS,
              validationFailureResponseCode.getJobTitleAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    jobTitleService.createJobTitle(jobTitleDto);

    logger.info("Job title saved successfully!");

    return ResponseEntity.ok(Constants.JOB_TITLE_CREATED);
  }

  @PutMapping(EndPointURI.JOB_TITLE)
  public ResponseEntity<Object> updateJobTitle(@Valid @RequestBody JobTitleDto jobTitleDto) {
    if (jobTitleService.updateExistsByJobTitle(jobTitleDto.getJobTitle(), jobTitleDto.getId())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(jobTitleDto.getJobTitle() + Constants.ALREADY_EXISTS,
              validationFailureResponseCode.getJobTitleAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    jobTitleService.updateJobTitle(jobTitleDto);

    logger.info("Job title updated successfully!");

    return ResponseEntity.ok(Constants.UPDATED);
  }

  @GetMapping(EndPointURI.JOB_TITLE)
  public ResponseEntity<Object> getAllJobTitle() {
    return ResponseEntity.ok(jobTitleService.readAllJobTitles());
  }

  @DeleteMapping(EndPointURI.JOB_TITLE_BY_ID)
  public ResponseEntity<Object> deleteJobTitle(@PathVariable Long id) {
    if (!jobTitleService.existsById(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.NOT_FOUND,
          validationFailureResponseCode.getJobTitleIdNotFound()), HttpStatus.BAD_REQUEST);
    }

    if (employeeService.existsByJobTitle(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.JOB_TITLE_DEPENDED,
          validationFailureResponseCode.getJobTitleDepended()), HttpStatus.BAD_REQUEST);
    }
    jobTitleService.deleteJobTitle(id);

    return ResponseEntity.ok(Constants.DELETED);
  }
}
