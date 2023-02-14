package com.invicta.human.resource.management.dto;

import com.invicta.human.resource.management.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DefectTrackerDto {

  private String employeeId;
  private String firstName;
  private String lastName;
  private String designationName;
  private String mail;
  private String contactNo;
  private Gender gender;
  private String password;
}
