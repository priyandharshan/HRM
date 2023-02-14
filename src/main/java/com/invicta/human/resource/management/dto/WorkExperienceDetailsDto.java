package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class WorkExperienceDetailsDto {

  private long id;
  private String companyName;
  private String experienceDesignation;
  private Date startDate;
  private Date finishDate;
  private String employeeId;
  private String description;
  private String reasonForLeaving;
}
