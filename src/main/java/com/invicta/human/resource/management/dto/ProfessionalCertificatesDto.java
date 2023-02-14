package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalCertificatesDto {
  private String employeeId;
  private long id;
  private String certificateName;
  private String institution;
  private String description;

}
