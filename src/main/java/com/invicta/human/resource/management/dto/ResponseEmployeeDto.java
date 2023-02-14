package com.invicta.human.resource.management.dto;

import com.invicta.human.resource.management.enums.AppointmentType;
import com.invicta.human.resource.management.enums.NameTitles;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ResponseEmployeeDto {

  private String id;
  private String firstName;
  private String lastName;
  private DesignationDto designationDto;
  private Date joinedDate;
  private NameTitles title;
  private boolean isAssessmentApplicable;
  private int assessmentPeriod;
  private boolean isDefectTrackerSystemUser;
  private EmploymentCategoryDto employmentCategoryDto;
  private JobTitleDto jobTitleDto;
}
