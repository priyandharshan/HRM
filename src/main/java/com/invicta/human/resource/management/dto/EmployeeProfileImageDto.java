package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeProfileImageDto {
  private long id;
  private String fileName;
  private String fileUrl;
  private String fileType;
}
