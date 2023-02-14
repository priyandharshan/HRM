package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmergencyContactDetailsDto {

  private long id;
  private String employeeId;
  private String name;
  private String phoneNumber;
  private String relationship;
}
