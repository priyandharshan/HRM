package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.LaptopAllocationDetailsDto;
import com.invicta.human.resource.management.entities.LaptopAllocationDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LaptopAllocationDetailsService {

  void saveLaptopAllocationDetails(LaptopAllocationDetails laptopAllocationDetails);

  LaptopAllocationDetails getLaptopAllocationDetaisByEmployeeId(String employeeId);

  void updateLaptopAllocationDetails(LaptopAllocationDetails laptopAllocationDetails);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<LaptopAllocationDetailsDto> getAllLaptopAllocationDetailsByEmployeeId(String employeeId);

  boolean isSerialNumberExists(String serialNumber);

  boolean isUpdateSerialNumberExists(String serialNumber, String employeeId);

}
