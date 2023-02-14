package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import com.invicta.human.resource.management.enums.VaccinesNames;

@Getter
@Setter
public class VaccinationDetailsDto {
  private long id;
  private VaccinesNames vaccineType;
  private String location;
  private String batchNumber;
  private Date vaccineDate;
  private String employeeId;

}
