package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExitProcedureDto {
  private String employeeId;
  private long id;
  private boolean clearence;
  private boolean exitInterview;
  private boolean bCardGiven;
  private boolean revokeOldId;
  private boolean experienceLetter;

}
