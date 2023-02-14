package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact_details")
@Getter
@Setter
public class ContactDetails extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String mobileNumber;
  @Column(unique = true)
  @Email
  @NotNull(message = "Email should not be empty")
  private String email;
  private String officialEmail;
  @Column(length = 500)
  private String permanentAddress;
  @Column(length = 500)
  private String temporaryAddress;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_Id", nullable = false)
  private Employee employee;
}
