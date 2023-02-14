package com.invicta.human.resource.management.repositories;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.invicta.human.resource.management.entities.Permission;
import com.invicta.human.resource.management.entities.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

  boolean existsByDesignationIdAndPermissionSubordinatePrivilegeSuperordinatePrivilegeId(
      Long roleId, Long id);

  boolean existsByDesignationIdAndPermissionSubordinatePrivilegeId(Long roleId, Long id);

  List<RolePermission> findAllByDesignationIdAndPermissionIn(Long roleId,
      Collection<Permission> permissions);

  List<RolePermission> findAllByDesignationId(Long roleId);

  void deleteAllByDesignationId(Long roleId);

  boolean existsByDesignationId(Long id);
}
