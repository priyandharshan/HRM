package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmployeeProfileImageService;
import com.invicta.human.resource.management.services.EmployeeService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
public class ProfileImageController {

  private static final Logger logger = LoggerFactory.getLogger(ProfileImageController.class);

  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeProfileImageService employeeProfileImageService;
  @Autowired
  private EmployeeService employeeService;

  @GetMapping(EndPointURI.GET_IMAGE)
  public ResponseEntity<Object> getImage(@PathVariable(value = "id") String employeeId,
      @PathVariable(value = "filename") String fileName, HttpServletRequest request) {
    if (employeeProfileImageService.isSameFileName(fileName, employeeId)) {
      logger.error(Constants.FILE_NOT_FOUND + "-filename:" + fileName);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.FILE_NOT_FOUND,
          validationFailureResponseCode.getFileNotFound()), HttpStatus.BAD_REQUEST);
    }
    Resource resource = null;
    try {
      resource = employeeProfileImageService.load(fileName, employeeId);
    } catch (RuntimeException e) {
      logger.error(Constants.FILE_NOT_FOUND + "-filename:" + fileName);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.FILE_NOT_FOUND,
          validationFailureResponseCode.getFileNotFound()), HttpStatus.BAD_REQUEST);
    }
    String contentType = null;
    try {
      if (resource != null) {
        contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (contentType == null) {
      contentType = "application/octet-stream";
    }
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=\"%s\"", resource.getFilename())).body(resource);

  }

  @DeleteMapping(EndPointURI.IMAGE)
  public ResponseEntity<Object> deleteImage(@PathVariable(value = "id") String employeeId) {
    if (!employeeProfileImageService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + employeeId);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    employeeProfileImageService.deleteImage(employeeId);
    logger.info(Constants.IMAGE_DELETE_SUCCESS);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.IMAGE_DELETE_SUCCESS), HttpStatus.OK);
  }

  @PutMapping(EndPointURI.IMAGE)
  public ResponseEntity<Object> updateImage(@RequestParam("images") MultipartFile multipartFile,
      @PathVariable(value = "id") String employeeId) {
    if (!employeeProfileImageService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + employeeId);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    employeeProfileImageService.updateImage(multipartFile, employeeId);
    logger.info(Constants.IMGAE_UPDATE_SUCCESS);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.IMGAE_UPDATE_SUCCESS), HttpStatus.OK);
  }

  @PostMapping(EndPointURI.IMAGE)
  public ResponseEntity<Object> addImage(@RequestParam("images") MultipartFile multipartFile,
      @PathVariable(value = "id") String employeeId) {

    if (!employeeService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + employeeId);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    if (employeeProfileImageService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.EMPLOEE_ID_ALREADY_EXISTS + ":" + employeeId);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOEE_ID_ALREADY_EXISTS,
          validationFailureResponseCode.getEmployeeIdAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    try {
      employeeProfileImageService.saveProfileImage(multipartFile, employeeId);
    } catch (IOException e) {
      logger.error(Constants.FILE_NOT_SUPPORTED);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.FILE_NOT_SUPPORTED,
          validationFailureResponseCode.getFileNotSupported()), HttpStatus.BAD_REQUEST);
    }
    logger.info(Constants.IMAGE_ADD_SUCCESS);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.IMAGE_ADD_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(EndPointURI.IMAGE)
  public ResponseEntity<Object> getImageDetails(@PathVariable(value = "id") String employeeId) {
    if (!employeeService.existsByEmployeeId(employeeId)) {
      logger.error(Constants.EMPLOYEE_NOT_FOUND + ":" + employeeId);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_NOT_FOUND,
          validationFailureResponseCode.getEmployeeNotExists()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(employeeProfileImageService.getImageDetails(employeeId),
        HttpStatus.OK);
  }
}
