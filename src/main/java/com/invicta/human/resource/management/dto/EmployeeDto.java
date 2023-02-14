package com.invicta.human.resource.management.dto;

import com.invicta.human.resource.management.enums.AppointmentType;
import com.invicta.human.resource.management.enums.NameTitles;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class EmployeeDto {

  private String id;
  private NameTitles title;
  private String firstName;
  private String lastName;
  private long designationId;
  private long employmentCategoryId;
  private long jobTitleId;
  private Date joinedDate;
  private AppointmentType appointmentType;
  private boolean isAssessmentApplicable;
  private int assessmentPeriod;
  private boolean isDefectTrackerSystemUser;
}
