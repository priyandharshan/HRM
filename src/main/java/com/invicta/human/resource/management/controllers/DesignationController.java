package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.DesignationDto;
import com.invicta.human.resource.management.dto.SearchDesiginationDto;
import com.invicta.human.resource.management.entities.Designation;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ContentResponse;
import com.invicta.human.resource.management.response.PaginatedContentResponse;
import com.invicta.human.resource.management.response.PaginatedContentResponse.Pagination;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.responseDto.DesiginationResponseDto;
import com.invicta.human.resource.management.services.DesignationService;
import com.invicta.human.resource.management.services.EmployeeService;
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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DesignationController {

  @Autowired
  private DesignationService designationService;
  @Autowired
  ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;

  Logger log = LoggerFactory.getLogger(DesignationController.class);

  // Add Designation
  @PostMapping(value = EndPointURI.DESIGNATION)
  public ResponseEntity<Object> addDesignation(@RequestBody DesignationDto designationdto) {
    if (designationService.isDesignation(designationdto.getDesignation())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.ALREADY_EXISTS,
          validationFailureResponseCode.getDesignationAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    Designation designation = new Designation();
    BeanUtils.copyProperties(designationdto, designation);
    designationService.saveDesignation(designation);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.ADD_DESIGNATION_SUCCESS),
        HttpStatus.OK);
  }

  // Get all Designation
  @GetMapping(value = EndPointURI.DESIGNATION)
  public ResponseEntity<Object> getAllDesignations() {
    List<Designation> designation;
    designation = designationService.getAllDesignation();
    return new ResponseEntity<>(designation, HttpStatus.OK);
  }

  // Get Designation By ID
  @GetMapping(value = EndPointURI.DESIGNATIONBYID)
  public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
    if (!designationService.existsByDesignationId(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NOT_EXISTS,
          validationFailureResponseCode.getDesignationNotexists()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(designationService.getById(id), HttpStatus.OK);

  }

  // Delete Designation
  @DeleteMapping(value = EndPointURI.DESIGNATIONBYID)
  public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
    if (!designationService.existsByDesignationId(id)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_NOT_EXISTS,
          validationFailureResponseCode.getDesignationNotexists()), HttpStatus.BAD_REQUEST);
    }
    if (employeeService.existsByDesignationId(id)) {
      log.error(Constants.DESIGNATION_DEPEND + id);
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.DESIGNATION_DEPEND,
          validationFailureResponseCode.getDesignationDepend()), HttpStatus.BAD_REQUEST);
    }
    designationService.deleteDesignation(id);
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.DELETE_DESIGNATION_SUCCESS),
        HttpStatus.OK);
  }

  // Update the Designation by designation
  @PutMapping(value = EndPointURI.DESIGNATION)
  public ResponseEntity<Object> updateDesignation(@RequestBody DesignationDto designationDto) {
    if (designationService.updateDesignationExists(designationDto.getDesignation(),
        designationDto.getId())) {
      return new ResponseEntity<Object>(new ValidationFailureResponse(Constants.ALREADY_EXISTS,
          validationFailureResponseCode.getDesignationAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    Designation designation = new Designation();
    BeanUtils.copyProperties(designationDto, designation);
    designationService.updateDesignation(designation);
    return new ResponseEntity<Object>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.UPDATE_DESIGNATION_SUCCESS),
        HttpStatus.OK);

  }

  @GetMapping(value = EndPointURI.DESIGNATION_SEARCH_PAGINATION)
  public ResponseEntity<ContentResponse<Object>> getLicenseSearch(
      SearchDesiginationDto searchDesiginationDto, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size, @RequestParam(name = "sortField") String sortField,
      @RequestParam(name = "direction") String direction) {
    Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sortField);
    Pagination pagination = new Pagination(page, size, 0, 0l);
    List<DesiginationResponseDto> designationDtoList =
        designationService.searchDesiginationPagination(searchDesiginationDto, pageable,
            pagination);
    return ResponseEntity.ok(
        new PaginatedContentResponse<>("desigination", designationDtoList, RestApiResponseStatus.OK,
            RestApiResponseStatus.OK.getStatus(), pagination));
  }
}
