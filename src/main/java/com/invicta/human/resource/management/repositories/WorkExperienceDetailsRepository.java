package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.WorkExperienceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceDetailsRepository
    extends JpaRepository<WorkExperienceDetails, Long> {

  List<WorkExperienceDetails> findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  boolean existsByCompanyNameIgnoreCaseAndExperienceDesignationIgnoreCaseAndEmployeeIdIgnoreCase(
      String companyName, String experienceDesignation, String employeeId);

  boolean existsByCompanyNameIgnoreCaseAndExperienceDesignationIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(
      String companyName, String experienceDesignation,String employeeId,Long id);

}
