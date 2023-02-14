package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class LaptopAllocationDetailsDto {
  private String employeeId;
  private long id;
  private String serialNumber;
  private String previousOwner;
  private boolean charger;
  private boolean networkCable;
  private Date assignedDate;
  private String usingLocation;
  private String remarks;
  private String assetTagName;

}
