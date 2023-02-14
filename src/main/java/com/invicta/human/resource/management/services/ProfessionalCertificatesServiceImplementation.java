package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ProfessionalCertificatesDto;
import com.invicta.human.resource.management.entities.ProfessionalCertificates;
import com.invicta.human.resource.management.repositories.ProfessionalCertificatesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionalCertificatesServiceImplementation
    implements ProfessionalCertificatesService {

  @Autowired
  private ProfessionalCertificatesRepository professionalCertificatesRepository;

  /**
   * Add Professional Certificates details
   *
   * @param professionalCertificates
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public void saveProfessionalCertificatesDetails(
      ProfessionalCertificates professionalCertificates) {
    professionalCertificatesRepository.save(professionalCertificates);

  }

  /**
   * Check employeeId exists or not
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return professionalCertificatesRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Check Id exists or not
   *
   * @param id
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public boolean existsById(Long id) {
    return professionalCertificatesRepository.existsById(id);
  }

  /**
   * Get Professional certificates by employeeId
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public ProfessionalCertificatesDto getProfessionalCertificateByEmployeeId(String employeeId) {
    return professionalCertificatesRepository.findByEmployeeId(employeeId);
  }

  /**
   * Update professional certificates
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public void updateProfessionalCertificates(ProfessionalCertificates professionalCertificates) {
    professionalCertificatesRepository.save(professionalCertificates);

  }

  /**
   * Delete professional certificates by id
   *
   * @param id
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public void deleteProfessionalCertificateDetails(long id) {
    professionalCertificatesRepository.deleteById(id);

  }

  /**
   * Get Professional Certificates details
   *
   * @param employeeId
   * @author Thenuga
   * @date 17-11-2022
   */
  @Override
  public List<ProfessionalCertificatesDto> getAllProfessionalCertificateDetailsByEmployeeId(
      String employeeId) {
    List<ProfessionalCertificatesDto> professionalCertificatesDtoList = new ArrayList<>();
    List<ProfessionalCertificates> professionalCertificatesList =
        professionalCertificatesRepository.findAllByEmployeeId(employeeId);
    for (ProfessionalCertificates professionalCertificates : professionalCertificatesList) {
      ProfessionalCertificatesDto professionalCertificatesDto = new ProfessionalCertificatesDto();
      BeanUtils.copyProperties(professionalCertificates, professionalCertificatesDto);
      professionalCertificatesDto.setEmployeeId(professionalCertificates.getEmployee().getId());
      professionalCertificatesDtoList.add(professionalCertificatesDto);
    }
    return professionalCertificatesDtoList;
  }

  /**
   * Check Professional Certificates Name and Employee Id details exists or not
   *
   * @param certificateName, employeeId
   * @author Thenuga
   * @date 18-11-2022
   */
  @Override
  public boolean existsBycertificateNameAndEmployeeId(String certificateName, String employeeId) {
    return professionalCertificatesRepository
        .existsByCertificateNameIgnoreCaseAndEmployeeIdIgnoreCase(certificateName, employeeId);
  }

  /**
   * Check Professional Certificates Name and Employee Id details exists or not when update
   *
   * @param certificateName, employeeId
   * @author Thenuga
   * @date 18-11-2022
   */
  @Override
  public boolean updateExistsBycertificateName(String certificateName, String employeeId, Long id) {
    return professionalCertificatesRepository
        .existsByCertificateNameIgnoreCaseAndEmployeeIdIgnoreCaseAndIdNot(certificateName,
            employeeId, id);
  }

}
