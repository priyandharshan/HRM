package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.EducationQualifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationQualificationsRepository
    extends JpaRepository<EducationQualifications, Long> {

  boolean existsByInstitutionsId(long Id);

  List<EducationQualifications> findAllByEmployeeId(String employeeId);

  boolean existsByInstitutionsInstituteName(String instituteName);

  boolean existsByEmployeeId(String employeeId);

  int countByEmployeeId(String employeeId);

  boolean existsByDegreeIgnoreCaseAndInstitutionsInstituteNameIgnoreCaseAndEmployeeIdIgnoreCase(
      String degree, String institute, String employeeId);

  boolean existsByDegreeIgnoreCaseAndInstitutionsInstituteNameIgnoreCaseAndEmployeeIdAndIdNot(
      String degree, String institute,
      String employeeId, Long id);

}
