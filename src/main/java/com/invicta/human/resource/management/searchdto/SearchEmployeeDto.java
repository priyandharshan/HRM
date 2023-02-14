package com.invicta.human.resource.management.searchdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchEmployeeDto {
  private String firstName;
  private String surname;
  private String id;
  private String designation;
  private String employmentCategory;
  private String email;
}
