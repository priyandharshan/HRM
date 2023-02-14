package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.KeyRegistryDto;
import com.invicta.human.resource.management.entities.KeyRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KeyRegistryService {

  void saveKeyRegistry(KeyRegistry keyRegistry);

  void updateKeyRegistry(KeyRegistry keyRegistry);

  KeyRegistry getKeyRegistryByEmployeeId(String employeeId);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<KeyRegistryDto> getAllKeyRegistryDetailsByEmployeeId(String employeeId);

  boolean isKeyNumberExists(String keyNumber);

  boolean isUpdateKeyNumberExists(String keyNumber, String employeeId);

}
