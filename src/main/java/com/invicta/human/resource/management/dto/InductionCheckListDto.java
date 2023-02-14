package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InductionCheckListDto {
  private String employeeId;
  private long id;
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
}
