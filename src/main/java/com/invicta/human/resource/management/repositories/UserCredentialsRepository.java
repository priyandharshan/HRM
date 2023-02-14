package com.invicta.human.resource.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invicta.human.resource.management.entities.UserCredentials;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

  UserCredentials findByEmployeeId(String employeeId);

  void deleteByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String id);

  UserCredentials findByUsername(String username);

  boolean existsByUsername(String username);
}
