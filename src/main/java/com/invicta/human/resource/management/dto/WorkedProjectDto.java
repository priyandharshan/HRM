package com.invicta.human.resource.management.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkedProjectDto {
  private long id;
  private String projectName;
  private String projectDescription;
  private String role;
  private Date projectStartDate;
  private Date projectFinishDate;
  private String technologyUsed;
  private long workExperienceDetailsId;

}
