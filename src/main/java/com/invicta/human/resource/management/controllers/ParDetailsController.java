package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.ParDetailsDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.ParDetails;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.services.ParDetailsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ParDetailsController {

  @Autowired
  ParDetailsService parDetailsService;

  @PostMapping(EndPointURI.ADD_PAR_DETAILS)
  public ResponseEntity<Object> createParDetails(@RequestBody ParDetailsDto parDetailsDto) {

    Employee employee = new Employee();
    employee.setId(parDetailsDto.getEmployeeId());

    ParDetails parDetails = new ParDetails();
    BeanUtils.copyProperties(parDetailsDto, parDetails);
    parDetails.setEmployee(employee);
    parDetailsService.saveParDetails(parDetails);

    return ResponseEntity.ok(
        new BasicResponse(RestApiResponseStatus.CREATED, Constants.PAR_DETAILS_CREATED));
  }

  @PutMapping(EndPointURI.PAR_DETAILS_LIST)
  public ResponseEntity<Object> updateParDetails(@RequestBody ParDetailsDto parDetailsDto) {

    Employee employee = new Employee();
    employee.setId(parDetailsDto.getEmployeeId());

    ParDetails parDetails = new ParDetails();
    BeanUtils.copyProperties(parDetailsDto, parDetails);
    parDetails.setEmployee(employee);
    parDetailsService.updateParDetails(parDetails);

    return ResponseEntity.ok(Constants.UPDATED_PAR_DETAILS);
  }

  @DeleteMapping(EndPointURI.PAR_DETAILS)
  public ResponseEntity<Object> deleteParDetails(@PathVariable long id) {
    parDetailsService.deleteParDetailsById(id);
    return ResponseEntity.ok(Constants.DELETED_PAR_DETAILS);
  }
}
