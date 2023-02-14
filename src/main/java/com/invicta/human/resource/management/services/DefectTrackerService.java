package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.DefectTrackerEmployeeDto;
import com.invicta.human.resource.management.dto.UserCredentialsDto;

import java.util.List;

public interface DefectTrackerService {

  String Verify(UserCredentialsDto userCredentialsDto);

  void defectTrackerDto(String id);

  void sendAsListToDefectTrackerSystem(List<DefectTrackerEmployeeDto> employeeIdList);

}
