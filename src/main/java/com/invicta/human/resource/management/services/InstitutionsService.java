package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.InstitutionsDto;
import com.invicta.human.resource.management.entities.Institutions;

import java.util.List;

public interface InstitutionsService {

  void saveInstitution(Institutions institutions);

  List<InstitutionsDto> getAllInstitutions();

  InstitutionsDto findInstitutionsDtoById(long id);

  boolean existsByInstituteName(String instituteName);

  InstitutionsDto findInstitutionsDtoByInstituteName(String instituteName);

}
