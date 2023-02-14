package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.EmployeeProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProfileImageRepository extends JpaRepository<EmployeeProfileImage, Long> {
  EmployeeProfileImage findByEmployeeId(String employeeId);

  boolean existsByFileName(String fileName);

  boolean existsByEmployeeId(String employeeId);

  void deleteByEmployeeId(String employeeId);

  EmployeeProfileImage getByEmployeeId(String employeeId);
}
