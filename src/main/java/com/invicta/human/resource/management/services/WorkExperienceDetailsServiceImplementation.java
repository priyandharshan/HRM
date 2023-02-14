package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.WorkExperienceDetailsDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.WorkExperienceDetails;
import com.invicta.human.resource.management.repositories.WorkExperienceDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkExperienceDetailsServiceImplementation implements WorkExperienceDetailsService {

  @Autowired
  private WorkExperienceDetailsRepository workExperienceDetailsRepository;

  /**
   * Save Work Experience Details Modified By @author Priyadharshan on 17-11-2022
   *
   * @param workExperienceDetailsDto to save work experience details
   */
  @Override
  public void saveWorkExperienceDetails(WorkExperienceDetailsDto workExperienceDetailsDto) {
    Employee employee = new Employee();
    employee.setId(workExperienceDetailsDto.getEmployeeId());
    WorkExperienceDetails workExperienceDetails = new WorkExperienceDetails();
    BeanUtils.copyProperties(workExperienceDetailsDto, workExperienceDetails);
    workExperienceDetails.setEmployee(employee);
    workExperienceDetailsRepository.save(workExperienceDetails);
  }

  /**
   * Delete Work Experience Details by id Modified Vy @author Priyadharshan on 17-11-2022
   *
   * @param id to delete work experience details
   */
  @Override
  public void deleteWorkExperienceDetailsById(long id) {
    workExperienceDetailsRepository.deleteById(id);
  }

  /**
   * Get All Work Experience Details By employeeId Modified By @author Priyadharshan on 17-11-2022
   *
   * @param employeeId to get all work experience details buy employeeId
   * @return workExperienceDetailsDtoList
   */
  @Override
  public List<WorkExperienceDetailsDto> getAllByEmployeeId(String employeeId) {
    List<WorkExperienceDetailsDto> workExperienceDetailsDtoList = new ArrayList<>();
    List<WorkExperienceDetails> workExperienceDetailsList =
        workExperienceDetailsRepository.findByEmployeeId(employeeId);
    for (WorkExperienceDetails workExperienceDetails : workExperienceDetailsList) {
      WorkExperienceDetailsDto workExperienceDetailsDto = new WorkExperienceDetailsDto();
      BeanUtils.copyProperties(workExperienceDetails, workExperienceDetailsDto);
      workExperienceDetailsDto.setEmployeeId(workExperienceDetails.getEmployee().getId());
      workExperienceDetailsDtoList.add(workExperienceDetailsDto);
    }
    return workExperienceDetailsDtoList;
  }

  /**
   * Check Work Experience Details By id Modified By @author Priyadharshan on 17-11-2022
   *
   * @param id delete By id
   * @return boolean
   */
  @Override
  public boolean existsById(long id) {
    return workExperienceDetailsRepository.existsById(id);
  }

  /**
   * Check Work Experience Details By employeeId Modified By @author Priyadharshan on 17-11-2022
   *
   * @param employeeId check with employeeId
   * @return boolean
   */

  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return workExperienceDetailsRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Check Company Name already exits when save work experience Details Modified By @author
   * Priyadharshan on 18-11-2022
   *
   * @param companyName check company Name exists
   * @param employeeId  check employeeId exists
   * @return boolean
   */
  @Override
  public boolean existsByCompanyNameSave(String companyName, String experienceDesignation,
      String employeeId) {
    return workExperienceDetailsRepository.existsByCompanyNameIgnoreCaseAndExperienceDesignationIgnoreCaseAndEmployeeIdIgnoreCase(
        companyName, experienceDesignation,employeeId);
  }

  /**
   * Check company name already exits when update work experience details Modified By @author
   * Priyadharshan on 18-11-2022
   *
   * @param companyName             check companyName
   * @param employeeId              check employeeId
   * @param workExperienceDetailsId
   * @return boolean
   */
  @Override
  public boolean existsByCompanyNameUpdate(String companyName,String experienceDesignation, String employeeId,
      Long workExperienceDetailsId) {
    return workExperienceDetailsRepository.existsByCompanyNameIgnoreCaseAndExperienceDesignationIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(
        companyName,experienceDesignation,employeeId,workExperienceDetailsId);
  }

}
