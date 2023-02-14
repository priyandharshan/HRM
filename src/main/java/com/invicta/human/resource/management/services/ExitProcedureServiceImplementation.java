package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ExitProcedureDto;
import com.invicta.human.resource.management.entities.ExitProcedure;
import com.invicta.human.resource.management.repositories.ExitProcedureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExitProcedureServiceImplementation implements ExitProcedureService {
  @Autowired
  private ExitProcedureRepository exitProcedureRepository;

  /**
   * Add Exit Procedure when update the employee
   *
   * @param exitProcedure
   * @author Thenuga
   * @date 14-11-2022
   */
  @Override
  public void saveExitProcedure(ExitProcedure exitProcedure) {
    exitProcedureRepository.save(exitProcedure);

  }

  /**
   * Get Exit Procedure by Employee Id when update the employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 15-11-2022
   */

  @Override
  public ExitProcedure getExitProcedureByEmployeeId(String employeeId) {
    return exitProcedureRepository.findByEmployeeId(employeeId);
  }

  /**
   * Update Exit Procedure when update the employee
   *
   * @param exitProcedure
   * @author Thenuga
   * @date 14-11-2022
   */
  @Override
  public void updateExitProcedure(ExitProcedure exitProcedure) {
    exitProcedureRepository.save(exitProcedure);

  }

  /**
   * Check id exists or not when update the employee
   *
   * @param id
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsById(Long id) {
    return exitProcedureRepository.existsById(id);
  }

  /**
   * Check employee id exists or not when update the employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return exitProcedureRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get all exit procedure for an employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<ExitProcedureDto> getAllExitProcedureDetailsByEmployeeId(String employeeId) {
    List<ExitProcedureDto> exitProcedureDtoList = new ArrayList<>();
    List<ExitProcedure> exitProceduresList =
        exitProcedureRepository.findAllByEmployeeId(employeeId);
    for (ExitProcedure exitProcedure : exitProceduresList) {
      ExitProcedureDto exitProcedureDto = new ExitProcedureDto();
      BeanUtils.copyProperties(exitProcedure, exitProcedureDto);
      exitProcedureDto.setEmployeeId(exitProcedure.getEmployee().getId());
      exitProcedureDtoList.add(exitProcedureDto);
    }
    return exitProcedureDtoList;
  }

}
