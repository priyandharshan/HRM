package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.EmployeeIDCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeIDCardRepository extends JpaRepository<EmployeeIDCard, Long> {

  EmployeeIDCard findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<EmployeeIDCard> findAllByEmployeeId(String employeeId);

}
