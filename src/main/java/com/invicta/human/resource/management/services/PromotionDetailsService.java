package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.PromotionDetailsDto;
import com.invicta.human.resource.management.entities.PromotionDetails;

import java.util.List;

public interface PromotionDetailsService {

  void savePromotionDetails(PromotionDetails promotionDetails);

  List<PromotionDetailsDto> getPromotionDetailsDtos();

  List<PromotionDetailsDto> getPromotionDtoList(String id);

  void deletePromotionDetails(long id);

  boolean existsByPromotionDesignation(Long designationId, String employeeId);

  boolean existsByUpdatePromotionDesignation(Long designationId, String employeeId, Long id);
}
