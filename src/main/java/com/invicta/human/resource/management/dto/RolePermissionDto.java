package com.invicta.human.resource.management.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolePermissionDto {
  private Long roleId;
  private Set<Long> permissionIds;
}
