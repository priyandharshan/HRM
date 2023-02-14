package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileViewDto {
  private String id;
  private String firstName;
  private String lastName;
  private String imageName;
  private String designation;
}
