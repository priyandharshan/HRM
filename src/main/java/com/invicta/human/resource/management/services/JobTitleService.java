package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.JobTitleDto;

import java.util.List;

public interface JobTitleService {
  void createJobTitle(JobTitleDto jobTitleDto);

  boolean existsByJobTitle(String jobTitle);

  void updateJobTitle(JobTitleDto jobTitleDto);

  boolean updateExistsByJobTitle(String jobTitle, Long id);

  List<JobTitleDto> readAllJobTitles();

  void deleteJobTitle(Long id);

  boolean existsById(Long id);
}
