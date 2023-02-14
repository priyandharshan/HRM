package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ParDetailsDto;
import com.invicta.human.resource.management.entities.ParDetails;

import java.util.List;

public interface ParDetailsService {

  void saveParDetails(ParDetails parDetails);

  List<ParDetailsDto> getParDetailsDtos();

  ParDetailsDto getParDetailsDto(String id);

  void updateParDetails(ParDetails parDetails);

  void deleteParDetailsById(long id);
}
