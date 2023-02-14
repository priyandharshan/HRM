package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.KeyRegistryDto;
import com.invicta.human.resource.management.entities.KeyRegistry;
import com.invicta.human.resource.management.repositories.KeyRegistryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyRegistryServiceImplementation implements KeyRegistryService {
  @Autowired
  private KeyRegistryRepository keyRegistryRepository;

  /**
   * Add Key Registry details when update the employee details
   *
   * @param keyRegistry - KeyRegistry object
   * @author Thenuga
   * @date 14-11-2022
   */
  @Override
  public void saveKeyRegistry(KeyRegistry keyRegistry) {
    keyRegistryRepository.save(keyRegistry);

  }

  /**
   * Edit Key Registry details when update the employee details
   *
   * @param keyRegistry - KeyRegistry object
   * @author Thenuga
   * @date 14-11-2022
   */
  @Override
  public void updateKeyRegistry(KeyRegistry keyRegistry) {
    keyRegistryRepository.save(keyRegistry);

  }

  /**
   * Edit Key Registry details by employee id
   *
   * @param keyRegistry - KeyRegistry object
   * @author Thenuga
   * @date 14-11-2022
   */

  @Override
  public KeyRegistry getKeyRegistryByEmployeeId(String employeeId) {
    return keyRegistryRepository.findByEmployeeId(employeeId);
  }

  /**
   * Check id exists or not
   *
   * @param id
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsById(Long id) {
    return keyRegistryRepository.existsById(id);
  }

  /**
   * Check employeeId exists or not
   *
   * @param employeeId
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return keyRegistryRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get all key registry details for an employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<KeyRegistryDto> getAllKeyRegistryDetailsByEmployeeId(String employeeId) {
    List<KeyRegistryDto> keyRegistryDtoList = new ArrayList<>();
    List<KeyRegistry> keyRegistryList = keyRegistryRepository.findAllByEmployeeId(employeeId);
    for (KeyRegistry keyRegistry : keyRegistryList) {
      KeyRegistryDto keyRegistryDto = new KeyRegistryDto();
      BeanUtils.copyProperties(keyRegistry, keyRegistryDto);
      keyRegistryDto.setEmployeeId(keyRegistry.getEmployee().getId());
      keyRegistryDtoList.add(keyRegistryDto);
    }
    return keyRegistryDtoList;
  }

  /**
   * Check Key Number exists or not
   *
   * @param keyNumber
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public boolean isKeyNumberExists(String keyNumber) {
    return keyRegistryRepository.existsByKeyNumber(keyNumber);
  }

  /**
   * Check Key Number exists or not when update without that employee Id
   *
   * @param keyNumber,employeeId
   * @author Thenuga
   * @date 22-11-2022
   */
  @Override
  public boolean isUpdateKeyNumberExists(String keyNumber, String employeeId) {
    return keyRegistryRepository.existsByKeyNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(keyNumber,
        employeeId);
  }

}
