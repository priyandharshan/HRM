package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.RefereesDetailsDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.RefereesDetails;
import com.invicta.human.resource.management.repositories.RefereesDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefereesDetailsServiceImplementation implements RefereesDetailsService {

  @Autowired
  private RefereesDetailsRepository refereesDetailsRepository;

  @Override
  public void saveRefereesDetails(RefereesDetailsDto refereesDetailsDto) {
    Employee employee = new Employee();
    employee.setId(refereesDetailsDto.getEmployeeId());
    RefereesDetails refereesDetails = new RefereesDetails();
    BeanUtils.copyProperties(refereesDetailsDto, refereesDetails);
    refereesDetails.setEmployee(employee);
    refereesDetailsRepository.save(refereesDetails);

  }

  @Override
  public void deleteRefereesDetailsById(long id) {
    refereesDetailsRepository.deleteById(id);
  }

  @Override
  public List<RefereesDetailsDto> getAllRefereesDetailsByEmployeeId(String employeeId) {
    List<RefereesDetails> refereesDetailsList =
        refereesDetailsRepository.findAllByEmployeeId(employeeId);
    List<RefereesDetailsDto> refereesDetailsDtoList = new ArrayList<>();
    for (RefereesDetails refereesDetails : refereesDetailsList) {
      RefereesDetailsDto refereesDetailsDto = new RefereesDetailsDto();
      BeanUtils.copyProperties(refereesDetails, refereesDetailsDto);
      refereesDetailsDto.setEmployeeId(refereesDetails.getEmployee().getId());
      refereesDetailsDtoList.add(refereesDetailsDto);
    }
    return refereesDetailsDtoList;
  }

  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return refereesDetailsRepository.existsByEmployeeId(employeeId);
  }

  @Override
  public boolean existsById(long id) {
    return refereesDetailsRepository.existsById(id);
  }

  @Override
  public boolean isUpdateContactNumber(String refereeContactNumber, String employeeId, Long id) {
    return refereesDetailsRepository.existsByRefereeContactNumberIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(
        refereeContactNumber, employeeId, id);
  }

  /**
   * @param refereeContactNumber contact number of a referee
   * @param employeeId           Employee's Id
   * @return true if contact number already exists for given employee id
   */
  @Override
  public boolean existsByPhoneNumberAndEmpId(String refereeContactNumber, String employeeId) {
    return refereesDetailsRepository.existsByRefereeContactNumberAndEmployeeIdIgnoreCase(
        refereeContactNumber, employeeId);
  }

  /**
   * @param refereeContactNumber Contact number of the Referee
   * @param employeeId           Employee's ID
   * @param id                   Primary key of referee's details
   * @return true if referees details already exists for given id
   */
  @Override
  public boolean existsByUpdateRefereesContact(String refereeContactNumber, String employeeId,
      long id) {
    return refereesDetailsRepository.existsByRefereeContactNumberIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(
        refereeContactNumber, employeeId, id);
  }
}
