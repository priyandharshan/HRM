package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EmployeeIDCardDto;
import com.invicta.human.resource.management.entities.EmployeeIDCard;
import com.invicta.human.resource.management.repositories.EmployeeIDCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeIDCardServiceImplementation implements EmployeeIDCardService {
  @Autowired
  private EmployeeIDCardRepository employeeIDCardRepository;

  /**
   * Add Employee Id card when update the employee
   *
   * @param employeeIDCard
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void saveEmployeeIDCard(EmployeeIDCard employeeIDCard) {
    employeeIDCardRepository.save(employeeIDCard);

  }

  /**
   * Get employee id card by Employee Id when update the employee
   *
   * @param employeeIDCard
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public EmployeeIDCard getEmployeeIDCardByEmployeeID(String employeeId) {
    return employeeIDCardRepository.findByEmployeeId(employeeId);
  }

  /**
   * Update Employee Id card when update the employee
   *
   * @param employeeIDCard
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void updateEmployeeIDCard(EmployeeIDCard employeeIDCard) {
    employeeIDCardRepository.save(employeeIDCard);

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
    return employeeIDCardRepository.existsById(id);
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
    return employeeIDCardRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get Employee Id Card details for an employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<EmployeeIDCardDto> getAllEmployeeIdCardDetailsByEmployeeId(String employeeId) {
    List<EmployeeIDCardDto> employeeIDCardDtoList = new ArrayList<>();
    List<EmployeeIDCard> employeeIDCardList =
        employeeIDCardRepository.findAllByEmployeeId(employeeId);
    for (EmployeeIDCard employeeIDCard : employeeIDCardList) {
      EmployeeIDCardDto employeeIDCardDto = new EmployeeIDCardDto();
      BeanUtils.copyProperties(employeeIDCard, employeeIDCardDto);
      employeeIDCardDto.setEmployeeId(employeeIDCard.getEmployee().getId());
      employeeIDCardDtoList.add(employeeIDCardDto);
    }
    return employeeIDCardDtoList;
  }

}
