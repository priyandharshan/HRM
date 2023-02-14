package com.invicta.human.resource.management.controllers;


import com.invicta.human.resource.management.dto.WorkedProjectDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.WorkExperienceDetailsService;
import com.invicta.human.resource.management.services.WorkedProjectsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class WorkedProjectsController {

  private static final Logger logger = LoggerFactory.getLogger(WorkedProjectsController.class);

  @Autowired
  private WorkExperienceDetailsService workExperienceDetailsService;
  @Autowired
  private WorkedProjectsService workedProjectsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;

  @PostMapping(EndPointURI.ADD_WORKED_PROJECTS)
  public ResponseEntity<Object> saveWorkedProject(@RequestBody WorkedProjectDto workedProjectDto) {
    if (!workExperienceDetailsService.existsById(workedProjectDto.getWorkExperienceDetailsId())) {
      logger.error(
          Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND + ":" + workedProjectDto.getWorkExperienceDetailsId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORK_EXPERIENCE_DETAILS_NOT_FOUND,
              validationFailureResponseCode.getWorkedProjectNotFoundInWorkExperienceDetails()),
          HttpStatus.BAD_REQUEST);
    }
    if (workedProjectsService.existsByProjectName(workedProjectDto.getProjectName(),
        workedProjectDto.getRole(), workedProjectDto.getWorkExperienceDetailsId())) {
      logger.error(
          Constants.PROMOTION_DETAILS_ALREADY_EXISTS + ":" + workedProjectDto.getWorkExperienceDetailsId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORKED_PROJECT_ALREADY_EXISTS_FOR_WORK_EXPERIENCE,
              validationFailureResponseCode.getWorkedProjectAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    workedProjectsService.saveWorkedProject(workedProjectDto);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.WORKED_PROJECTS_CREATED));
  }

  @PutMapping(EndPointURI.ADD_WORKED_PROJECTS)
  public ResponseEntity<Object> updateWorkedProject(
      @RequestBody WorkedProjectDto workedProjectDto) {
    if (!workedProjectsService.existsByWorkExperienceDetailsId(
        workedProjectDto.getWorkExperienceDetailsId())) {
      logger.error(
          Constants.WORKED_PROJECTS_NOT_FOUND_FOR_WORK_EXPERIENCE_DETAILS + ":" + workedProjectDto.getWorkExperienceDetailsId());
      return new ResponseEntity<>(new ValidationFailureResponse(
          Constants.WORKED_PROJECTS_NOT_FOUND_FOR_WORK_EXPERIENCE_DETAILS,
          validationFailureResponseCode.getWorkedProjectNotFoundInWorkExperienceDetails()),
          HttpStatus.BAD_REQUEST);
    }
    if (!workedProjectsService.existsById(workedProjectDto.getId())) {
      logger.error(Constants.WORKED_PROJECTS_NOT_FOUND + ":" + workedProjectDto.getId());
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.WORKED_PROJECTS_NOT_FOUND,
          validationFailureResponseCode.getWorkedProjectNotFound()), HttpStatus.BAD_REQUEST);
    }
    if (workedProjectsService.existsByUpdateProjectName(workedProjectDto.getProjectName(),
        workedProjectDto.getRole(), workedProjectDto.getWorkExperienceDetailsId(),
        workedProjectDto.getId())) {
      logger.error(
          Constants.WORKED_PROJECT_ALREADY_EXISTS_FOR_WORK_EXPERIENCE + ":" + workedProjectDto.getWorkExperienceDetailsId());
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.WORKED_PROJECT_ALREADY_EXISTS_FOR_WORK_EXPERIENCE,
              validationFailureResponseCode.getWorkedProjectAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    workedProjectsService.saveWorkedProject(workedProjectDto);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.WORKED_PROJECTS_UPDATED));
  }

  @DeleteMapping(EndPointURI.WORKED_PROJECTS_BY_ID)
  public ResponseEntity<Object> deleteWorkedProjectById(@PathVariable(value = "id") long id) {
    if (!workedProjectsService.existsById(id)) {
      logger.error(Constants.WORKED_PROJECTS_NOT_FOUND + ":" + id);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.WORKED_PROJECTS_NOT_FOUND,
          validationFailureResponseCode.getWorkedProjectNotFound()), HttpStatus.BAD_REQUEST);
    }
    workedProjectsService.deleteWorkedProjectById(id);
    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.OK, Constants.WORKED_PROJECTS_DELETED));
  }

  @GetMapping(EndPointURI.WORKED_PROJECTS_BY_WORK_EXPERIENCE_DETAILS_ID)
  public ResponseEntity<Object> getAllWorkedProjectsByWorkExperienceDetails(
      @PathVariable(value = "workExperienceDetailsId") long workExperienceDetailsId) {
    if (!workedProjectsService.existsByWorkExperienceDetailsId(workExperienceDetailsId)) {
      logger.error(
          Constants.WORKED_PROJECTS_NOT_FOUND_FOR_WORK_EXPERIENCE_DETAILS + ":" + workExperienceDetailsId);
      return new ResponseEntity<>(new ValidationFailureResponse(
          Constants.WORKED_PROJECTS_NOT_FOUND_FOR_WORK_EXPERIENCE_DETAILS,
          validationFailureResponseCode.getWorkedProjectNotFoundInWorkExperienceDetails()),
          HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(
        workedProjectsService.getAllProjectByWorkExperienceDetailsId(workExperienceDetailsId));
  }

}
