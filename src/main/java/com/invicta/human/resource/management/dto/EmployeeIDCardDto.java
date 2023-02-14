package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class EmployeeIDCardDto {
  private String employeeId;
  private long id;
  private Date givenDate;
  private Date handedDate;
  private boolean lost;
  private Date lostDate;
  private boolean complaintCopy;
  private Date informDate;
  private boolean policeComplaint;

}
