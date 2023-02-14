package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.VaccinationDetailsDto;

import java.util.List;

public interface VaccinationDetailsService {

  void saveVaccinationDetails(VaccinationDetailsDto vaccinationDetailsDto);

  List<VaccinationDetailsDto> getAllVaccinationDetails();

  List<VaccinationDetailsDto> getAllVaccinationDetailsByEmployeeId(String employeeId);

  void deleteVaccinationDetailsById(long id);

  boolean existsByEmployeeId(String employeeId);

  boolean existsById(long id);

}
