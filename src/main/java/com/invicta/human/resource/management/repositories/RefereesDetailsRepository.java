package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.RefereesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereesDetailsRepository extends JpaRepository<RefereesDetails, Long> {

  List<RefereesDetails> findAllByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  boolean existsByRefereeContactNumberAndEmployeeIdIgnoreCase(String refereeContactNumber, String employeeId);

  boolean existsByRefereeContactNumberIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(
      String refereeContactNumber, String employeeId,Long Id);



}
