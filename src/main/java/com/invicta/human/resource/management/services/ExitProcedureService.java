package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ExitProcedureDto;
import com.invicta.human.resource.management.entities.ExitProcedure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExitProcedureService {

  void saveExitProcedure(ExitProcedure exitProcedure);

  ExitProcedure getExitProcedureByEmployeeId(String employeeId);

  void updateExitProcedure(ExitProcedure exitProcedure);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<ExitProcedureDto> getAllExitProcedureDetailsByEmployeeId(String employeeId);

}
