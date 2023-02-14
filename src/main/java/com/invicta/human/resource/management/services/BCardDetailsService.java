package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.BCardDetailsDto;
import com.invicta.human.resource.management.entities.BCardDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BCardDetailsService {

  void saveBCardDetails(BCardDetails bCardDetails);

  BCardDetails getBCardDetailsByEmployeeId(String employeeId);

  void updateBCardDetails(BCardDetails bCardDetails);

  void deleteBCardDetails(long id);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<BCardDetailsDto> getAllBCardDetailsByEmployeeId(String employeeId);

  boolean isEpfNumberExists(Long epfNumber);

  boolean isUpdateEpfNumberExists(Long epfNumber, String employeeId);

}
