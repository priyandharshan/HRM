package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubordinatePrivilegeDto {
  private Long id;
  private String name;
  private boolean status;
  private List<PermissionDto> PermissionDtos;

}
