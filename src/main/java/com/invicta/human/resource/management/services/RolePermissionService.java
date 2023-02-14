package com.invicta.human.resource.management.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.invicta.human.resource.management.dto.RolePermissionDto;
import com.invicta.human.resource.management.dto.SuperordinatePrivilegeDto;

@Service
public interface RolePermissionService {

  List<SuperordinatePrivilegeDto> getPrivilegeByRoleId(Long id);

  boolean isSameCollectionOfPermissions(RolePermissionDto permissionDto);

  void saveAndUpdateRolePermission(RolePermissionDto permissionDto);

  List<SuperordinatePrivilegeDto> getPrivilegeByUsername(String name);

  RolePermissionDto getPrivilegesByRoleId(Long id);


}
