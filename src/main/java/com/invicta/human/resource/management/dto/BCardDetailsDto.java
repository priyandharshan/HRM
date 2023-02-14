package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BCardDetailsDto {
  private String employeeId;
  private long id;
  private Long epfNumber;
  private Date createdDate;
  private Date courieredDate;
  private Boolean isApproval;
  private String approvalRejectedReason;
  private Date receivedDate;
  private boolean handedOver;

}
