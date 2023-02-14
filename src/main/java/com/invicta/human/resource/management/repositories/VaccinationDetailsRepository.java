package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.VaccinationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationDetailsRepository extends JpaRepository<VaccinationDetails, Long> {

  List<VaccinationDetails> findAllByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

}
