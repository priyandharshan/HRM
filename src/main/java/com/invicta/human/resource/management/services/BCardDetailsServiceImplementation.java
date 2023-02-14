package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.BCardDetailsDto;
import com.invicta.human.resource.management.entities.BCardDetails;
import com.invicta.human.resource.management.repositories.BCardDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BCardDetailsServiceImplementation implements BCardDetailsService {
  @Autowired
  private BCardDetailsRepository bCardDetailsRepository;

  /**
   * Add B-Card details when update the employee
   *
   * @param bCardDetails
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void saveBCardDetails(BCardDetails bCardDetails) {
    bCardDetailsRepository.save(bCardDetails);
  }

  /**
   * Get B-Card details by employee id when update the employee
   *
   * @param bCardDetails
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public BCardDetails getBCardDetailsByEmployeeId(String employeeId) {
    return bCardDetailsRepository.findByEmployeeId(employeeId);
  }

  /**
   * Update B-Card details when update the employee
   *
   * @param bCardDetails
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void updateBCardDetails(BCardDetails bCardDetails) {
    bCardDetailsRepository.save(bCardDetails);

  }

  @Override
  public void deleteBCardDetails(long id) {
    bCardDetailsRepository.deleteById(id);

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
    return bCardDetailsRepository.existsById(id);
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
    return bCardDetailsRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get All B-Card Details for an employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<BCardDetailsDto> getAllBCardDetailsByEmployeeId(String employeeId) {
    List<BCardDetailsDto> bCardDetailsDtoList = new ArrayList<>();
    List<BCardDetails> bCardDetailsList = bCardDetailsRepository.findAllByEmployeeId(employeeId);
    for (BCardDetails bCardDetails : bCardDetailsList) {
      BCardDetailsDto bCardDetailsDto = new BCardDetailsDto();
      BeanUtils.copyProperties(bCardDetails, bCardDetailsDto);
      bCardDetailsDto.setEmployeeId(bCardDetails.getEmployee().getId());
      bCardDetailsDtoList.add(bCardDetailsDto);
    }
    return bCardDetailsDtoList;
  }

  /**
   * Check EPF No exits or not
   *
   * @param epfNumber
   * @author Thenuga
   * @date 13-12-2022
   */
  @Override
  public boolean isEpfNumberExists(Long epfNumber) {
    return bCardDetailsRepository.existsByEpfNumber(epfNumber);
  }

  @Override
  public boolean isUpdateEpfNumberExists(Long epfNumber, String employeeId) {
    return bCardDetailsRepository.existsByEpfNumberAndEmployeeIdIgnoreCaseNot(epfNumber,
        employeeId);
  }

}
