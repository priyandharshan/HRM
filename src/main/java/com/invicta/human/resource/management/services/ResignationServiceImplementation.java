package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ResignationDto;
import com.invicta.human.resource.management.entities.Resignation;
import com.invicta.human.resource.management.repositories.ResignationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResignationServiceImplementation implements ResignationService {
  @Autowired
  private ResignationRepository resignationRepository;

  /**
   * Add resignation when update the employee
   *
   * @param resignation
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public void saveResignation(Resignation resignation) {
    resignationRepository.save(resignation);

  }

  /**
   * Get resignation by employee id when update the employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 15-11-2022
   */
  @Override
  public Resignation getResignationByEmployeeId(String employeeId) {
    return resignationRepository.findByEmployeeId(employeeId);
  }

  /**
   * Update resignation when update the employee
   *
   * @param resignation
   * @author Thenuga
   * @date 15-11-2022
   */

  @Override
  public void UpdateResignation(Resignation resignation) {
    resignationRepository.save(resignation);

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
    return resignationRepository.existsById(id);
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
    return resignationRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get Resignation Details for an employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<ResignationDto> getAllResignationDetailsByEmployeeId(String employeeId) {
    List<ResignationDto> resignationDtoList = new ArrayList<>();
    List<Resignation> resignationList = resignationRepository.findAllByEmployeeId(employeeId);
    for (Resignation resignation : resignationList) {
      ResignationDto resignationDto = new ResignationDto();
      BeanUtils.copyProperties(resignation, resignationDto);
      resignationDto.setEmployeeId(resignation.getEmployee().getId());
      resignationDtoList.add(resignationDto);
    }
    return resignationDtoList;
  }

}
