package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.VaccinationDetailsDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.VaccinationDetails;
import com.invicta.human.resource.management.repositories.VaccinationDetailsRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationDetailsServiceImplementation implements VaccinationDetailsService {

  @Autowired
  private VaccinationDetailsRepository vaccinationDetailsRepository;

  /**
   * Save Vaccination details Modified By @author Priyadharshan on 2022-11-15
   *
   * @param vaccinationDetailsDto
   */

  @Override
  public void saveVaccinationDetails(VaccinationDetailsDto vaccinationDetailsDto) {
    Employee employee = new Employee();
    employee.setId(vaccinationDetailsDto.getEmployeeId());
    VaccinationDetails vaccinationDetails = new VaccinationDetails();
    vaccinationDetails.setEmployee(employee);
    BeanUtils.copyProperties(vaccinationDetailsDto, vaccinationDetails);
    vaccinationDetailsRepository.save(vaccinationDetails);
  }

  /**
   * Get all VaccinationDetails Modified by @author Priyadharshan on 2022-11-15
   *
   * @return VaccinationDetailsDtoList
   */

  @Override
  public List<VaccinationDetailsDto> getAllVaccinationDetails() {
    List<VaccinationDetailsDto> vaccinationDetailsDtoList = new ArrayList<>();
    List<VaccinationDetails> vaccinationDetailsList = vaccinationDetailsRepository.findAll();
    for (VaccinationDetails vaccinationDetails : vaccinationDetailsList) {
      VaccinationDetailsDto vaccinationDetailsDto = new VaccinationDetailsDto();
      BeanUtils.copyProperties(vaccinationDetails, vaccinationDetailsDto);
      vaccinationDetailsDto.setEmployeeId(vaccinationDetails.getEmployee().getId());
      vaccinationDetailsDtoList.add(vaccinationDetailsDto);
    }
    return vaccinationDetailsDtoList;
  }

  /**
   * Get All vaccination Details by employeeId Modified by @author Priyadharshan on 2022-11-15
   *
   * @param employeeId
   * @return VaccinationDetailsDto
   */

  @Override
  public List<VaccinationDetailsDto> getAllVaccinationDetailsByEmployeeId(String employeeId) {
    List<VaccinationDetailsDto> vaccinationDetailsDtoList = new ArrayList<>();
    List<VaccinationDetails> vaccinationDetailsList =
        vaccinationDetailsRepository.findAllByEmployeeId(employeeId);
    for (VaccinationDetails vaccinationDetails : vaccinationDetailsList) {
      VaccinationDetailsDto vaccinationDetailsDto = new VaccinationDetailsDto();
      BeanUtils.copyProperties(vaccinationDetails, vaccinationDetailsDto);
      vaccinationDetailsDto.setEmployeeId(vaccinationDetails.getEmployee().getId());
      vaccinationDetailsDtoList.add(vaccinationDetailsDto);
    }
    return vaccinationDetailsDtoList;
  }

  /**
   * Delete Vaccination Details Modified By @author Priyadharshan on 2022-11-15
   *
   * @param id
   */
  @Override
  public void deleteVaccinationDetailsById(long id) {
    vaccinationDetailsRepository.deleteById(id);
  }

  /**
   * check employeeId is Exists Modified by @author Priyadharshan on 15-11-2022
   *
   * @param employeeId
   * @return boolean
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return vaccinationDetailsRepository.existsByEmployeeId(employeeId);
  }

  /**
   * check id is exists for vaccination details Modified by @author Priyadharshan on 15-11-2022
   *
   * @param id
   * @return boolean
   */

  @Override
  public boolean existsById(long id) {
    return vaccinationDetailsRepository.existsById(id);
  }

}
