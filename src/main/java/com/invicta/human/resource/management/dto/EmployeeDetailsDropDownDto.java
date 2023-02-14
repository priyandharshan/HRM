package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDetailsDropDownDto {
  private String employeeId;
  private String firstName;
  private String lastName;
  private boolean isDefectTrackerSystemUser;
  private String designation;
  private boolean isActive;
}
