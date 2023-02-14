package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.LaptopAllocationDetailsDto;
import com.invicta.human.resource.management.entities.LaptopAllocationDetails;
import com.invicta.human.resource.management.repositories.LaptopAllocationDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopAllocationDetailsServiceImplementation
    implements LaptopAllocationDetailsService {
  @Autowired
  private LaptopAllocationDetailsRepository laptopAllocationDetailsRepository;

  /**
   * Add laptop allocation details when update the employee details
   *
   * @param laptopAllocationDetails - LaptopAllocationDetails object
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void saveLaptopAllocationDetails(LaptopAllocationDetails laptopAllocationDetails) {
    laptopAllocationDetailsRepository.save(laptopAllocationDetails);

  }

  /**
   * Get laptop allocation details by Employee ID when update the employee details
   *
   * @param laptopAllocationDetails - LaptopAllocationDetails object
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public LaptopAllocationDetails getLaptopAllocationDetaisByEmployeeId(String employeeId) {
    return laptopAllocationDetailsRepository.findByEmployeeId(employeeId);
  }

  /**
   * Update laptop allocation details when update the employee details
   *
   * @param laptopAllocationDetails - LaptopAllocationDetails object
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void updateLaptopAllocationDetails(LaptopAllocationDetails laptopAllocationDetails) {
    laptopAllocationDetailsRepository.save(laptopAllocationDetails);

  }

  /**
   * Check id exists or not when update the employee details
   *
   * @param laptopAllocationDetails - LaptopAllocationDetails object
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsById(Long id) {
    return laptopAllocationDetailsRepository.existsById(id);
  }

  /**
   * Check employee id exists or not when update the employee details
   *
   * @param laptopAllocationDetails - LaptopAllocationDetails object
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return laptopAllocationDetailsRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get laptop allocation details by Employee ID
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<LaptopAllocationDetailsDto> getAllLaptopAllocationDetailsByEmployeeId(
      String employeeId) {
    List<LaptopAllocationDetailsDto> laptopAllocationDetailsDtoList = new ArrayList<>();
    List<LaptopAllocationDetails> laptopAllocationDetailsList =
        laptopAllocationDetailsRepository.findAllByEmployeeId(employeeId);
    for (LaptopAllocationDetails laptopAllocationDetails : laptopAllocationDetailsList) {
      LaptopAllocationDetailsDto laptopAllocationDetailsDto = new LaptopAllocationDetailsDto();
      BeanUtils.copyProperties(laptopAllocationDetails, laptopAllocationDetailsDto);
      laptopAllocationDetailsDto.setEmployeeId(laptopAllocationDetails.getEmployee().getId());
      laptopAllocationDetailsDtoList.add(laptopAllocationDetailsDto);
    }
    return laptopAllocationDetailsDtoList;
  }

  /**
   * Check Laptop serial number exists or not
   *
   * @param serialNumber
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public boolean isSerialNumberExists(String serialNumber) {
    return laptopAllocationDetailsRepository.existsBySerialNumber(serialNumber);
  }

  /**
   * Check Laptop serial number exists or not without that employee id when update
   *
   * @param serialNumber,employeeId
   * @author Thenuga
   * @date 22-11-2022
   */
  @Override
  public boolean isUpdateSerialNumberExists(String serialNumber, String employeeId) {
    return laptopAllocationDetailsRepository.existsBySerialNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(
        serialNumber, employeeId);
  }

}
