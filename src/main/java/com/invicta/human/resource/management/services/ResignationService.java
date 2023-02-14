package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ResignationDto;
import com.invicta.human.resource.management.entities.Resignation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResignationService {

  void saveResignation(Resignation resignation);

  Resignation getResignationByEmployeeId(String employeeId);

  void UpdateResignation(Resignation resignation);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<ResignationDto> getAllResignationDetailsByEmployeeId(String employeeId);

}
