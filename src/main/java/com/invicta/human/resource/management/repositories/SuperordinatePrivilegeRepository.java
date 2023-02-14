package com.invicta.human.resource.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invicta.human.resource.management.entities.SuperordinatePrivilege;

@Repository
public interface SuperordinatePrivilegeRepository
    extends JpaRepository<SuperordinatePrivilege, Long> {

}
