package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LeaveManagementDto {
  private String employeeId;
  private Date joinedDate;
}
