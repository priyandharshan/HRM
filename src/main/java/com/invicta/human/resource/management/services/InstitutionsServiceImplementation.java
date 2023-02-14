package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.InstitutionsDto;
import com.invicta.human.resource.management.entities.Institutions;
import com.invicta.human.resource.management.repositories.InstitutionsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionsServiceImplementation implements InstitutionsService {

  @Autowired
  private InstitutionsRepository institutionsRepository;

  /**
   * Save Institution Modified By @author Priyadharshan on 18-11-2022
   *
   * @param institutions
   */
  @Override
  public void saveInstitution(Institutions institutions) {
    institutionsRepository.save(institutions);
  }

  /**
   * Get all institutions Modified By @author Priyadharshan on 18-11-2022
   *
   * @return institutionDtoList
   */
  @Override
  public List<InstitutionsDto> getAllInstitutions() {
    List<InstitutionsDto> institutionsDtoList = new ArrayList<>();
    List<Institutions> institutionsList = institutionsRepository.findAll();
    for (Institutions institutions : institutionsList) {
      InstitutionsDto institutionsDto = new InstitutionsDto();
      BeanUtils.copyProperties(institutions, institutionsDto);
      institutionsDtoList.add(institutionsDto);
    }
    return institutionsDtoList;
  }

  /**
   * Get Institution by id Modified By @author Priaydharshan on 18-11-2022
   *
   * @param id
   * @return institutionDto
   */

  @Override
  public InstitutionsDto findInstitutionsDtoById(long id) {
    Institutions institutions = institutionsRepository.findById(id).get();
    InstitutionsDto institutionsDto = new InstitutionsDto();
    BeanUtils.copyProperties(institutions, institutionsDto);
    return institutionsDto;
  }

  /**
   * Check institute Name alreadyExits Modified By @Author Priyadharshan o 18-11-2022
   *
   * @param instituteName
   * @return boolean
   */
  @Override
  public boolean existsByInstituteName(String instituteName) {
    return institutionsRepository.existsByInstituteName(instituteName);
  }

  /**
   * Find Institutions by instituteName Modified By @Author Priyadharshan on 18-11-2022
   *
   * @param instituteName
   * @return institutionDto
   */
  @Override
  public InstitutionsDto findInstitutionsDtoByInstituteName(String instituteName) {
    Institutions institutions = institutionsRepository.findByInstituteName(instituteName);
    InstitutionsDto institutionsDto = new InstitutionsDto();
    BeanUtils.copyProperties(institutions, institutionsDto);
    return institutionsDto;
  }

}
