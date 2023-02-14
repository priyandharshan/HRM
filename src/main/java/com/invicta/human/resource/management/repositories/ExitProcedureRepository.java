package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.ExitProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExitProcedureRepository extends JpaRepository<ExitProcedure, Long> {

  ExitProcedure findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<ExitProcedure> findAllByEmployeeId(String employeeId);

}
