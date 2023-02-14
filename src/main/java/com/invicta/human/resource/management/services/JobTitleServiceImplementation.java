package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.JobTitleDto;
import com.invicta.human.resource.management.entities.JobTitle;
import com.invicta.human.resource.management.repositories.JobTitleRepo;
import com.invicta.human.resource.management.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleServiceImplementation implements JobTitleService {

  @Autowired
  JobTitleRepo jobTitleRepo;

  /**
   * @param jobTitleDto Dto of Job title entity
   */
  @Override
  public void createJobTitle(JobTitleDto jobTitleDto) {
    JobTitle jobTitle = new JobTitle();
    BeanUtils.copyProperties(jobTitleDto, jobTitle);
    jobTitleRepo.save(jobTitle);
  }

  /**
   * @param jobTitle Job title
   * @return true if job title already exists
   */
  @Override
  public boolean existsByJobTitle(String jobTitle) {
    return jobTitleRepo.existsByJobTitleIgnoreCase(jobTitle);
  }

  /**
   * @param jobTitleDto Dto of Job title entity
   */
  @Override
  public void updateJobTitle(JobTitleDto jobTitleDto) {
    JobTitle jobTitle = new JobTitle();
    BeanUtils.copyProperties(jobTitleDto, jobTitle);
    jobTitleRepo.save(jobTitle);
  }

  /**
   * @param jobTitle Job title
   * @return true if job title already exists for any other id
   */
  @Override
  public boolean updateExistsByJobTitle(String jobTitle, Long id) {
    return jobTitleRepo.existsByJobTitleIgnoreCaseAndIdNot(jobTitle, id);
  }

  /**
   * @return All Job titles
   */
  @Override
  public List<JobTitleDto> readAllJobTitles() {
    return jobTitleRepo.findAllByJobTitleIsNotOrderByJobTitleAsc(Constants.ADMIN_ROLE);
  }

  /**
   * @param id Job title id
   */
  @Override
  public void deleteJobTitle(Long id) {
    jobTitleRepo.deleteById(id);
  }

  /**
   * @param id Job title id
   * @return true if given id job title exists in the db table
   */
  @Override
  public boolean existsById(Long id) {
    return jobTitleRepo.existsById(id);
  }
}
