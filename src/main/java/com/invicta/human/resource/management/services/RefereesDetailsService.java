package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.RefereesDetailsDto;

import java.util.List;

public interface RefereesDetailsService {

  void saveRefereesDetails(RefereesDetailsDto refereesDetailsDto);

  void deleteRefereesDetailsById(long id);

  List<RefereesDetailsDto> getAllRefereesDetailsByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  boolean existsById(long id);

  boolean isUpdateContactNumber(String refereeContactNumber, String employeeId, Long id);

  boolean existsByPhoneNumberAndEmpId(String refereeContactNumber, String employeeId);

  boolean existsByUpdateRefereesContact(String refereeContactNumber, String employeeId, long id);
}
