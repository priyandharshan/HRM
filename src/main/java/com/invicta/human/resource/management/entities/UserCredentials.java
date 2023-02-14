package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
@Getter
@Setter
public class UserCredentials extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private boolean isUserActive;
  private String password;
  private String token;
  private boolean active;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_Id")
  private Employee employee;
}
