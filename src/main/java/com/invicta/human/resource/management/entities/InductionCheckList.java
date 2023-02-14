package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InductionCheckList extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private boolean applicationForm;
  private boolean nicCopy;
  private boolean birthCertificate;
  private boolean updatedCV;
  private boolean photoSoftCopy;
  private boolean policeClearence;
  private boolean gsCharecter;
  private boolean olResultSheet;
  private boolean alResultSheet;
  private boolean schoolLeaving;
  private boolean schoolCharacter;
  private boolean higherEducationCertificates;
  private boolean otherCertificates;
  private boolean appointmentLetter;
  private boolean offerLetter;
  private boolean isBcardGiven;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
