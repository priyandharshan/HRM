package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ResignationDto {
  private String employeeId;
  private long id;
  private Date submittedDate;
  private Date approvedDate;
  private Date effectiveDate;
  private String reasonForLeaving;
  private boolean resignationLetter;
  private boolean resignationAcceptance;

}
