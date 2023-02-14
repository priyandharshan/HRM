package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EmployeeIDCardDto;
import com.invicta.human.resource.management.entities.EmployeeIDCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeIDCardService {

  void saveEmployeeIDCard(EmployeeIDCard employeeIDCard);

  EmployeeIDCard getEmployeeIDCardByEmployeeID(String employeeId);

  void updateEmployeeIDCard(EmployeeIDCard employeeIDCard);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<EmployeeIDCardDto> getAllEmployeeIdCardDetailsByEmployeeId(String employeeId);

}
