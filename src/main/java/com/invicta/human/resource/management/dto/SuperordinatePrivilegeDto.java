package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuperordinatePrivilegeDto {
  private Long id;
  private String name;
  private List<SubordinatePrivilegeDto> subordinatePrivileges;
}
