package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDto {
  private Long id;
  private String name;
  private boolean status;
  private String description;
}
