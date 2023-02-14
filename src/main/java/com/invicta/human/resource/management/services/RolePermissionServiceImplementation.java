package com.invicta.human.resource.management.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.invicta.human.resource.management.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invicta.human.resource.management.dto.PermissionDto;
import com.invicta.human.resource.management.dto.RolePermissionDto;
import com.invicta.human.resource.management.dto.SubordinatePrivilegeDto;
import com.invicta.human.resource.management.dto.SuperordinatePrivilegeDto;
import com.invicta.human.resource.management.repositories.RolePermissionRepository;
import com.invicta.human.resource.management.repositories.SuperordinatePrivilegeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolePermissionServiceImplementation implements RolePermissionService {
  @Autowired
  private SuperordinatePrivilegeRepository superordinatePrivilegeRepositories;
  @Autowired
  private RolePermissionRepository rolePermissionRepository;
  @Autowired
  private UserCredentialsService userCredentialsService;
  @Autowired
  private EmployeeService employeeService;

  @Override
  public List<SuperordinatePrivilegeDto> getPrivilegeByRoleId(Long roleId) {
    List<SuperordinatePrivilegeDto> superordinatePrivilegeDtos =
        new ArrayList<SuperordinatePrivilegeDto>();
    List<SuperordinatePrivilege> superordinatePrivileges =
        superordinatePrivilegeRepositories.findAll();
    for (SuperordinatePrivilege superordinatePrivilege : superordinatePrivileges) {
      if (rolePermissionRepository.existsByDesignationIdAndPermissionSubordinatePrivilegeSuperordinatePrivilegeId(
          roleId, superordinatePrivilege.getId())) {
        SuperordinatePrivilegeDto superordinatePrivilegeDto = new SuperordinatePrivilegeDto();
        superordinatePrivilegeDto.setId(superordinatePrivilege.getId());
        superordinatePrivilegeDto.setName(superordinatePrivilege.getName());
        List<SubordinatePrivilegeDto> subordinatePrivileges = new ArrayList<>();
        superordinatePrivilege.getSubordinatePrivilege().forEach(subordinatePrivilege -> {
          if (rolePermissionRepository.existsByDesignationIdAndPermissionSubordinatePrivilegeId(
              roleId, subordinatePrivilege.getId())) {
            SubordinatePrivilegeDto subordinatePrivilegeDto = new SubordinatePrivilegeDto();
            List<PermissionDto> permissionDtos = new ArrayList<>();
            subordinatePrivilegeDto.setId(subordinatePrivilege.getId());
            subordinatePrivilegeDto.setName(subordinatePrivilege.getName());
            subordinatePrivilegeDto.setStatus(true);
            rolePermissionRepository.findAllByDesignationIdAndPermissionIn(roleId,
                subordinatePrivilege.getPermission()).forEach(rolePermission -> {
              PermissionDto permissionDto = new PermissionDto();
              permissionDto.setId(rolePermission.getPermission().getId());
              permissionDto.setName(rolePermission.getPermission().getName());
              permissionDto.setDescription(rolePermission.getPermission().getDescription());
              permissionDto.setStatus(true);
              permissionDtos.add(permissionDto);
            });
            subordinatePrivilegeDto.setPermissionDtos(permissionDtos);
            subordinatePrivileges.add(subordinatePrivilegeDto);
          }
        });
        superordinatePrivilegeDto.setSubordinatePrivileges(subordinatePrivileges);
        superordinatePrivilegeDtos.add(superordinatePrivilegeDto);
      }
    }
    return superordinatePrivilegeDtos;
  }


  private Long getUserIdByUserName(String name) {
    return userCredentialsService.getUserCredentialsByUsername(name).getId();
  }

  @Override
  public boolean isSameCollectionOfPermissions(RolePermissionDto permissionDto) {
    Set<Long> permissionIds = new HashSet<>();
    rolePermissionRepository.findAllByDesignationId(permissionDto.getRoleId())
        .forEach(rolePermission -> {
          permissionIds.add(rolePermission.getPermission().getId());
        });
    return permissionIds.equals(permissionDto.getPermissionIds());
  }

  @Transactional
  public void saveAndUpdateRolePermission(RolePermissionDto permissionDto) {
    rolePermissionRepository.deleteAllByDesignationId(permissionDto.getRoleId());
    permissionDto.getPermissionIds().forEach((id) -> {
      RolePermission rolePermission = new RolePermission();
      rolePermission.setId(0L);
      Designation designation = new Designation();
      designation.setId(permissionDto.getRoleId());
      rolePermission.setDesignation(designation);
      Permission permission = new Permission();
      permission.setId(id);
      rolePermission.setPermission(permission);
      rolePermission.setDesignation(designation);
      rolePermission.setStatus(true);
      rolePermissionRepository.save(rolePermission);
    });

  }

  @Override
  public List<SuperordinatePrivilegeDto> getPrivilegeByUsername(String name) {
    Designation designation =
        userCredentialsService.getUserCredentialsByUsername(name).getEmployee().getDesignation();
    List<SuperordinatePrivilegeDto> superordinatePrivilegeDtos =
        new ArrayList<SuperordinatePrivilegeDto>();
    List<SuperordinatePrivilege> superordinatePrivileges =
        superordinatePrivilegeRepositories.findAll();
    for (SuperordinatePrivilege superordinatePrivilege : superordinatePrivileges) {
      if (rolePermissionRepository.existsByDesignationIdAndPermissionSubordinatePrivilegeSuperordinatePrivilegeId(
          designation.getId(), superordinatePrivilege.getId())) {
        SuperordinatePrivilegeDto superordinatePrivilegeDto = new SuperordinatePrivilegeDto();
        superordinatePrivilegeDto.setId(superordinatePrivilege.getId());
        superordinatePrivilegeDto.setName(superordinatePrivilege.getName());
        List<SubordinatePrivilegeDto> subordinatePrivileges = new ArrayList<>();
        superordinatePrivilege.getSubordinatePrivilege().forEach(subordinatePrivilege -> {
          if (rolePermissionRepository.existsByDesignationIdAndPermissionSubordinatePrivilegeId(
              designation.getId(), subordinatePrivilege.getId())) {
            SubordinatePrivilegeDto subordinatePrivilegeDto = new SubordinatePrivilegeDto();
            List<PermissionDto> permissionDtos = new ArrayList<>();
            subordinatePrivilegeDto.setId(subordinatePrivilege.getId());
            subordinatePrivilegeDto.setName(subordinatePrivilege.getName());
            subordinatePrivilegeDto.setStatus(true);
            rolePermissionRepository.findAllByDesignationIdAndPermissionIn(designation.getId(),
                subordinatePrivilege.getPermission()).forEach(rolePermission -> {
              PermissionDto permissionDto = new PermissionDto();
              permissionDto.setId(rolePermission.getPermission().getId());
              permissionDto.setName(rolePermission.getPermission().getName());
              permissionDto.setDescription(rolePermission.getPermission().getDescription());
              permissionDto.setStatus(true);
              permissionDtos.add(permissionDto);
            });
            subordinatePrivilegeDto.setPermissionDtos(permissionDtos);
            subordinatePrivileges.add(subordinatePrivilegeDto);
          }
        });
        superordinatePrivilegeDto.setSubordinatePrivileges(subordinatePrivileges);
        superordinatePrivilegeDtos.add(superordinatePrivilegeDto);
      }
    }
    return superordinatePrivilegeDtos;
  }
  @Override
  public RolePermissionDto  getPrivilegesByRoleId(Long id) {
    List<RolePermission> rolePermissions=rolePermissionRepository.findAllByDesignationId(id);
    RolePermissionDto rolePermissionDto=new RolePermissionDto();
    rolePermissionDto.setRoleId(id);
    Set<Long> permissionIds=new HashSet<>();
    for (RolePermission rolePermission : rolePermissions) {
      permissionIds.add(rolePermission.getPermission().getId());
    }
    rolePermissionDto.setPermissionIds(permissionIds);
    return rolePermissionDto;
  }

}
