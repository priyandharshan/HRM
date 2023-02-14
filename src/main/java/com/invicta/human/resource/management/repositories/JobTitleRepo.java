package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.dto.JobTitleDto;
import com.invicta.human.resource.management.entities.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTitleRepo extends JpaRepository<JobTitle, Long> {

  boolean existsByJobTitleIgnoreCase(String jobTitle);

  boolean existsByJobTitleIgnoreCaseAndIdNot(String jobTitle, Long id);

  List<JobTitleDto> findAllByJobTitleIsNotOrderByJobTitleAsc(String jobTitle);

}
