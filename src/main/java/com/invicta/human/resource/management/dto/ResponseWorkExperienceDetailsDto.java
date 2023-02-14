package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ResponseWorkExperienceDetailsDto {

  private Long id;
  @NotNull
  private String companyName;
  @NotNull
  private String experienceDesignation;
  @NotNull
  private Date startDate;
  @NotNull
  private Date finishDate;
  private String description;
  private String employeeId;
  private List<WorkedProjectDto> workedProjects;
}
