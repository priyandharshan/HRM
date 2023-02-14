package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDetailsDto {

  private String employeeId;
  private String mobileNumber;
  private String email;
  private String officialEmail;
  private String permanentAddress;
  private String temporaryAddress;
}
