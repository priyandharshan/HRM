package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.WorkedProject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkedProjectsRepository extends JpaRepository<WorkedProject, Long> {

  List<WorkedProject> findByWorkExperienceDetailsId(long workExperienceDetailsId);

  boolean existsByWorkExperienceDetailsId(long workExperienceDetailsId);

  boolean existsByProjectNameIgnoreCaseAndRoleIgnoreCaseAndWorkExperienceDetailsId(
      String projectName, String role, long workExperienceDetailsId);

  boolean existsByRoleIgnoreCaseAndWorkExperienceDetailsId(String role,
      long workExperienceDetailsId);

  boolean existsByProjectNameIgnoreCaseAndRoleIgnoreCaseAndWorkExperienceDetailsIdAndIdNot(String projectName,
      String role, long workExperienceDetailsId, long id);
}
