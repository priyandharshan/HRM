package com.invicta.human.resource.management.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invicta.human.resource.management.dto.PermissionDto;
import com.invicta.human.resource.management.dto.SubordinatePrivilegeDto;
import com.invicta.human.resource.management.dto.SuperordinatePrivilegeDto;
import com.invicta.human.resource.management.repositories.SuperordinatePrivilegeRepository;

@Service
public class PermissionServiceImplementation implements PermissionService {

  @Autowired
  private SuperordinatePrivilegeRepository superordinatePrivilegeRepositories;

  @Override
  public List<SuperordinatePrivilegeDto> getAllPermission() {
    List<SuperordinatePrivilegeDto> superordinatePrivilegeDtos = new ArrayList<>();
    superordinatePrivilegeRepositories.findAll().forEach((superordinatePrivilege) -> {
      SuperordinatePrivilegeDto superordinatePrivilegeDto = new SuperordinatePrivilegeDto();
      superordinatePrivilegeDto.setId(superordinatePrivilege.getId());
      superordinatePrivilegeDto.setName(superordinatePrivilege.getName());
      List<SubordinatePrivilegeDto> subordinatePrivilegeDtos = new ArrayList<>();
      superordinatePrivilege.getSubordinatePrivilege().forEach((subordinatePrivilege) -> {
        SubordinatePrivilegeDto subordinatePrivilegeDto = new SubordinatePrivilegeDto();
        subordinatePrivilegeDto.setId(subordinatePrivilege.getId());
        subordinatePrivilegeDto.setName(subordinatePrivilege.getName());
        List<PermissionDto> permissionDtos = new ArrayList<>();
        subordinatePrivilege.getPermission().forEach((permission) -> {
          PermissionDto permissionDto = new PermissionDto();
          permissionDto.setId(permission.getId());
          permissionDto.setName(permission.getName());
          permissionDto.setDescription(permission.getDescription());
          permissionDtos.add(permissionDto);
        });
        subordinatePrivilegeDto.setPermissionDtos(permissionDtos);
        subordinatePrivilegeDtos.add(subordinatePrivilegeDto);
      });
      superordinatePrivilegeDto.setSubordinatePrivileges(subordinatePrivilegeDtos);
      superordinatePrivilegeDtos.add(superordinatePrivilegeDto);

    });
    return superordinatePrivilegeDtos;
  }


}
