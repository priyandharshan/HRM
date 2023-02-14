package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ParDetailsDto {

  private long id;
  private String employeeId;
  private int performanceRating;
  private int communicationRating;
  private int technicalRating;
  private Date reviewedDate;
}
