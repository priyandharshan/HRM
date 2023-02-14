package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.WorkedProjectDto;

import java.util.List;

public interface WorkedProjectsService {

  void saveWorkedProject(WorkedProjectDto workedProjectDto);

  void deleteWorkedProjectById(long id);

  List<WorkedProjectDto> getAllProjectByWorkExperienceDetailsId(long workExperienceDetailsId);

  boolean existsByWorkExperienceDetailsId(long workExperienceDetailsId);

  boolean existsById(long id);

  boolean existsByProjectName(String projectName, String role, long workExperienceDetailsId);

  boolean existsByDesignation(String role, long workExperienceDetailsId);

  boolean existsByUpdateProjectName(String projectName, String role, long workExperienceDetailsId,
      long id);
}
