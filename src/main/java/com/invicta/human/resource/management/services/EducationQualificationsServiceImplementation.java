package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EducationQualificationDto;
import com.invicta.human.resource.management.dto.InstitutionsDto;
import com.invicta.human.resource.management.entities.EducationQualifications;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.Institutions;
import com.invicta.human.resource.management.repositories.EducationQualificationsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationQualificationsServiceImplementation
    implements EducationQualificationsService {

  @Autowired
  private EducationQualificationsRepository educationQualificationsRepository;
  @Autowired
  private InstitutionsService institutionsService;

  @Override
  public void saveEducationQualification(EducationQualificationDto educationQualificationDto) {
    save(educationQualificationDto);
  }

  @Override
  public void deleteEducationQualification(long id) {
    educationQualificationsRepository.deleteById(id);
  }

  @Override
  public void updateEducationQualification(EducationQualificationDto educationQualificationDto) {
    save(educationQualificationDto);
  }

  @Override
  public List<EducationQualificationDto> getAllEducationQualificationByEmployeeId(
      String employeeId) {
    List<EducationQualificationDto> educationQualificationDtoList = new ArrayList<>();
    List<EducationQualifications> educationQualificationsList =
        educationQualificationsRepository.findAllByEmployeeId(employeeId);
    for (EducationQualifications educationQualifications : educationQualificationsList) {
      EducationQualificationDto educationQualificationDto = new EducationQualificationDto();
      BeanUtils.copyProperties(educationQualifications, educationQualificationDto);
      educationQualificationDto.setEmployeeId(educationQualifications.getEmployee().getId());
      educationQualificationDto
          .setInstituteName(educationQualifications.getInstitutions().getInstituteName());
      educationQualificationDtoList.add(educationQualificationDto);
    }
    return educationQualificationDtoList;
  }

  @Override
  public boolean existsByInstituteName(String instituteName) {
    return educationQualificationsRepository.existsByInstitutionsInstituteName(instituteName);
  }

  @Override
  public boolean existsById(long id) {
    return educationQualificationsRepository.existsById(id);
  }

  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return educationQualificationsRepository.existsByEmployeeId(employeeId);
  }

  @Override
  public int countByEmployeeId(String employeeId) {
    return educationQualificationsRepository.countByEmployeeId(employeeId);
  }

  public void save(EducationQualificationDto educationQualificationDto) {
    Employee employee = new Employee();
    Institutions institutions = new Institutions();
    EducationQualifications educationQualifications = new EducationQualifications();
    BeanUtils.copyProperties(educationQualificationDto, educationQualifications);
    if (institutionsService.existsByInstituteName(educationQualificationDto.getInstituteName())) {
      InstitutionsDto institutionsDto = institutionsService
          .findInstitutionsDtoByInstituteName(educationQualificationDto.getInstituteName());
      BeanUtils.copyProperties(institutionsDto, institutions);
    } else if (!institutionsService
        .existsByInstituteName(educationQualificationDto.getInstituteName())
        || institutionsService.findInstitutionsDtoByInstituteName(
        educationQualificationDto.getInstituteName()) == null) {
      institutions.setInstituteName(educationQualificationDto.getInstituteName());
      institutionsService.saveInstitution(institutions);
      educationQualifications.setInstitutions(institutions);
      educationQualifications.setEmployee(employee);
    }
    employee.setId(educationQualificationDto.getEmployeeId());
    educationQualifications.setInstitutions(institutions);
    educationQualifications.setEmployee(employee);
    educationQualificationsRepository.save(educationQualifications);
  }

  @Override
  public boolean existsByDegree(String degree, String institute, String employeeId) {
    return educationQualificationsRepository.existsByDegreeIgnoreCaseAndInstitutionsInstituteNameIgnoreCaseAndEmployeeIdIgnoreCase(
        degree, institute, employeeId);
  }

  @Override
  public boolean existsByUpdateDegree(String degree, String institute, String employeeId, Long id) {
    return educationQualificationsRepository.existsByDegreeIgnoreCaseAndInstitutionsInstituteNameIgnoreCaseAndEmployeeIdAndIdNot(
        degree, institute, employeeId, id);
  }

}
