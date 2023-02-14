package com.invicta.human.resource.management.entities;

import com.invicta.human.resource.management.enums.BloodGroup;
import com.invicta.human.resource.management.enums.Gender;
import com.invicta.human.resource.management.enums.MaritialStatus;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

@Entity
@Table(name = "personal_details")
@Getter
@Setter
public class PersonalDetails extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull(message = "gender should not be null")
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @NotEmpty(message = "can not be an empty string")
  @Enumerated(EnumType.STRING)
  private MaritialStatus maritalStatus;
  @Enumerated(EnumType.STRING)
  private BloodGroup bloodGroup;
  @NotNull(message = "NIC number should not be null")
  private String nicNumber;
  private String passportNumber;
  private String drivingLicenseNumber;
  private Date licenseExpiry;
  private Date passportExpiry;
  @Past
  private Date dob;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
