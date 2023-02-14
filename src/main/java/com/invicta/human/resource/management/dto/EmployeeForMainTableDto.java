package com.invicta.human.resource.management.dto;

import com.invicta.human.resource.management.enums.AppointmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeForMainTableDto {

  private String employeeID;
  private String firstName;
  private String surname;
  private DesignationDto designationDto;
  private String email;
  private EmployeeCategoryDto employeeCategoryDto;
}
