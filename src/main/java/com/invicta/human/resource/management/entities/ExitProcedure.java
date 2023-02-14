package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ExitProcedure extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private boolean clearence;
  private boolean exitInterview;
  private boolean bCardGiven;
  private boolean revokeOldId;
  private boolean experienceLetter;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
