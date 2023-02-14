package com.invicta.human.resource.management.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invicta.human.resource.management.entities.KeyRegistry;

@Repository
public interface KeyRegistryRepository extends JpaRepository<KeyRegistry, Long> {

  KeyRegistry findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<KeyRegistry> findAllByEmployeeId(String employeeId);

  boolean existsByKeyNumber(String keyNumber);

  boolean existsByKeyNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(String keyNumber,
      String employeeId);

}
