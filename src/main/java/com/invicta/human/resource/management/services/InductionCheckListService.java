package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.InductionCheckListDto;
import com.invicta.human.resource.management.entities.InductionCheckList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InductionCheckListService {

  void saveInductionCheckList(InductionCheckList inductionCheckList);

  InductionCheckList getInductionCheckListByEmployeeID(String employeeId);

  void updateInductionCheckList(InductionCheckList inductionCheckList);

  boolean existsById(Long id);

  boolean existsByEmployeeId(String employeeId);

  List<InductionCheckListDto> getAllInductionCheckListDetailsByEmployeeId(String employeeId);

}
