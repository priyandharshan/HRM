package com.invicta.human.resource.management.dto;

import com.invicta.human.resource.management.enums.BloodGroup;
import com.invicta.human.resource.management.enums.Gender;
import com.invicta.human.resource.management.enums.MaritialStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PersonalDetailsDto {

  private long id;
  private Gender gender;
  private MaritialStatus maritalStatus;
  private BloodGroup bloodGroup;
  private String nicNumber;
  private String passportNumber;
  private String drivingLicenseNumber;
  private Date passportExpiry;
  private Date licenseExpiry;
  private Date dob;
  private String employeeId;
}
