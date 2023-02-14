package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.BCardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BCardDetailsRepository extends JpaRepository<BCardDetails, Long> {

  BCardDetails findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<BCardDetails> findAllByEmployeeId(String employeeId);

  boolean existsByEpfNumber(Long epfNumber);

  boolean existsByEpfNumberAndEmployeeIdIgnoreCaseNot(Long epfNumber, String employeeId);

}
