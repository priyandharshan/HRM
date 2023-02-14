package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.WorkExperienceDetailsDto;

import java.util.List;

public interface WorkExperienceDetailsService {

  void saveWorkExperienceDetails(WorkExperienceDetailsDto workExperienceDetailsDto);

  void deleteWorkExperienceDetailsById(long id);

  List<WorkExperienceDetailsDto> getAllByEmployeeId(String employeeId);

  boolean existsById(long id);

  boolean existsByEmployeeId(String employeeId);

  boolean existsByCompanyNameSave(String companyName, String experienceDesignation,
      String employeeId);

  boolean existsByCompanyNameUpdate(String companyName, String experienceDesignation,
      String employeeId,Long workExperienceDetailsId);

}
