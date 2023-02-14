package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ProfessionalCertificatesDto;
import com.invicta.human.resource.management.entities.ProfessionalCertificates;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessionalCertificatesService {

  void saveProfessionalCertificatesDetails(ProfessionalCertificates professionalCertificates);

  boolean existsByEmployeeId(String employeeId);

  boolean existsById(Long id);

  ProfessionalCertificatesDto getProfessionalCertificateByEmployeeId(String employeeId);

  void updateProfessionalCertificates(ProfessionalCertificates professionalCertificates);

  void deleteProfessionalCertificateDetails(long id);

  List<ProfessionalCertificatesDto> getAllProfessionalCertificateDetailsByEmployeeId(
      String employeeId);

  boolean existsBycertificateNameAndEmployeeId(String certificateName, String employeeId);

  boolean updateExistsBycertificateName(String certificateName, String employeeId, Long id);

}
