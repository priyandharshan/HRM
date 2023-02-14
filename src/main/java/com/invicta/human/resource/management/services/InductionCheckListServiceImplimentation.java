package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.InductionCheckListDto;
import com.invicta.human.resource.management.entities.InductionCheckList;
import com.invicta.human.resource.management.repositories.InductionCheckListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InductionCheckListServiceImplimentation implements InductionCheckListService {
  @Autowired
  private InductionCheckListRepository inductionCheckListRepository;

  /**
   * Add Induction check list when update the employee
   *
   * @param inductionCheckList
   * @author Thenuga
   * @date 08-11-2022
   */
  @Override
  public void saveInductionCheckList(InductionCheckList inductionCheckList) {
    inductionCheckListRepository.save(inductionCheckList);

  }

  /**
   * Get Induction check list by Employee Id when update the employee
   *
   * @param employeeId
   * @author Thenuga
   * @date 15-11-2022
   */

  @Override
  public InductionCheckList getInductionCheckListByEmployeeID(String employeeId) {
    return inductionCheckListRepository.findByEmployeeId(employeeId);
  }

  /**
   * update Induction check list when update the employee
   *
   * @param inductionCheckList
   * @author Thenuga
   * @date 15-11-2022
   */

  @Override
  public void updateInductionCheckList(InductionCheckList inductionCheckList) {
    inductionCheckListRepository.save(inductionCheckList);

  }

  /**
   * check induction check list id as exists or not
   *
   * @param inductionCheckList
   * @author Thenuga
   * @date 16-11-2022
   */

  @Override
  public boolean existsById(Long id) {
    return inductionCheckListRepository.existsById(id);
  }

  /**
   * check employee id as exists or not
   *
   * @param inductionCheckList
   * @author Thenuga
   * @date 16-11-2022
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return inductionCheckListRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Get all Induction check list details by Employee Id
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<InductionCheckListDto> getAllInductionCheckListDetailsByEmployeeId(
      String employeeId) {
    List<InductionCheckListDto> inductionCheckListDtoList = new ArrayList<>();
    List<InductionCheckList> inductionCheckListsList =
        inductionCheckListRepository.findAllByEmployeeId(employeeId);
    for (InductionCheckList inductionCheckList : inductionCheckListsList) {
      InductionCheckListDto inductionCheckListDto = new InductionCheckListDto();
      BeanUtils.copyProperties(inductionCheckList, inductionCheckListDto);
      inductionCheckListDto.setEmployeeId(inductionCheckList.getEmployee().getId());
      inductionCheckListDtoList.add(inductionCheckListDto);
    }
    return inductionCheckListDtoList;
  }

}
