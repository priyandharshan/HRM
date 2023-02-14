package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Resignation extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date submittedDate;
  private Date approvedDate;
  private Date effectiveDate;
  @Column(length = 1000)
  private String reasonForLeaving;
  private boolean resignationLetter;
  private boolean resignationAcceptance;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
