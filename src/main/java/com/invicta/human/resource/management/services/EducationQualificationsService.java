package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EducationQualificationDto;

import java.util.List;

public interface EducationQualificationsService {

  void saveEducationQualification(EducationQualificationDto educationQualificationsDto);

  void deleteEducationQualification(long id);

  void updateEducationQualification(EducationQualificationDto educationQualificationDto);

  List<EducationQualificationDto> getAllEducationQualificationByEmployeeId(String employeeId);

  boolean existsByInstituteName(String instituteName);

  boolean existsById(long id);

  boolean existsByEmployeeId(String employeeId);

  int countByEmployeeId(String employeeId);

  boolean existsByDegree(String degree, String institute,String employeeId);

  boolean existsByUpdateDegree(String degree,String institute,String employeeId,Long id);

}
