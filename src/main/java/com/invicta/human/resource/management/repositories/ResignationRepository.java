package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.Resignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResignationRepository extends JpaRepository<Resignation, Long> {

  Resignation findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<Resignation> findAllByEmployeeId(String employeeId);

}
