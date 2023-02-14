package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class EducationQualificationDto {
  private long id;
  private Date educationQualificationStartDate;
  private Date educationQualificationFinishDate;
  private String educationQualificationDescription;
  private String degree;
  private String grade;
  private String instituteName;
  private String employeeId;

}
