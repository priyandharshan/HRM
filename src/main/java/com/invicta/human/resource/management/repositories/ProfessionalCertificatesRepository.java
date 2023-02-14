package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.dto.ProfessionalCertificatesDto;
import com.invicta.human.resource.management.entities.ProfessionalCertificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalCertificatesRepository
    extends JpaRepository<ProfessionalCertificates, Long> {

  boolean existsByEmployeeId(String employeeId);

  ProfessionalCertificatesDto findByEmployeeId(String employeeId);

  List<ProfessionalCertificates> findAllByEmployeeId(String employeeId);

  boolean existsByCertificateNameIgnoreCaseAndEmployeeIdIgnoreCase(String certificateName,
      String employeeId);

  boolean existsByCertificateNameIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(String certificateName,
      String employeeId, Long id);

}
