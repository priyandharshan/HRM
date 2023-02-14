package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.LaptopAllocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopAllocationDetailsRepository
    extends JpaRepository<LaptopAllocationDetails, Long> {

  LaptopAllocationDetails findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<LaptopAllocationDetails> findAllByEmployeeId(String employeeId);

  boolean existsBySerialNumber(String serialNumber);

  boolean existsBySerialNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(String serialNumber,
      String employeeId);

}
