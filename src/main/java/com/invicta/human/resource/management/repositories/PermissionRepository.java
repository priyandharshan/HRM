package com.invicta.human.resource.management.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invicta.human.resource.management.entities.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
