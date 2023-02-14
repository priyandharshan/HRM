package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.PromotionDetailsDto;
import com.invicta.human.resource.management.entities.PromotionDetails;
import com.invicta.human.resource.management.repositories.PromotionDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionDetailsServiceImplementation implements PromotionDetailsService {

  @Autowired
  PromotionDetailsRepository promotionDetailsRepository;

  /**
   * Saves promotional details into database
   *
   * @param promotionDetails - Promotion details object
   * @author Dhivyaruban
   * @date 27-10-2022
   */
  @Override
  public void savePromotionDetails(PromotionDetails promotionDetails) {
    promotionDetailsRepository.save(promotionDetails);
  }

  /**
   * Gets all promotion details from database
   *
   * @return Promotional details dtos
   * @author Dhivyaruban
   * @date 27-10-2022
   */
  @Override
  public List<PromotionDetailsDto> getPromotionDetailsDtos() {
    List<PromotionDetailsDto> promotionDetailsDtos = new ArrayList<>();
    List<PromotionDetails> promotionDetailsList = promotionDetailsRepository.findAll();

    for (PromotionDetails promotionDetails : promotionDetailsList) {
      PromotionDetailsDto promotionDetailsDto = new PromotionDetailsDto();
      BeanUtils.copyProperties(promotionDetails, promotionDetailsDto);
      promotionDetailsDto.setDesignationId(promotionDetails.getPromotedDesignation().getId());
      promotionDetailsDtos.add(promotionDetailsDto);
    }

    return promotionDetailsDtos;
  }

  /**
   * Gets employee by id and converts to DTO
   *
   * @param id - Employee id
   * @return Promotion details DTO
   * @author Dhivyaruban
   * @date 27-10-2022
   */
  @Override
  public List<PromotionDetailsDto> getPromotionDtoList(String id) {
    List<PromotionDetails> promotionDetailsList =
        promotionDetailsRepository.findAllByEmployeeId(id);
    List<PromotionDetailsDto> promotionDetailsDtoList = new ArrayList<>();

    for (PromotionDetails promotionDetails : promotionDetailsList) {
      PromotionDetailsDto promotionDetailsDto = new PromotionDetailsDto();
      BeanUtils.copyProperties(promotionDetails, promotionDetailsDto);
      promotionDetailsDto.setDesignationId(promotionDetails.getPromotedDesignation().getId());
      promotionDetailsDtoList.add(promotionDetailsDto);
    }

    return promotionDetailsDtoList;
  }

  /**
   * @param id - Promotion details id
   * @author Dhivyaruban
   * @date
   */

  @Transactional
  public void deletePromotionDetails(long id) {
    promotionDetailsRepository.deleteById(id);
  }

  @Override
  public boolean existsByPromotionDesignation(Long designationId, String employeeId) {
    return promotionDetailsRepository.existsByPromotedDesignationIdAndEmployeeIdIgnoreCase(
        designationId, employeeId);
  }

  @Override
  public boolean existsByUpdatePromotionDesignation(Long designationId, String employeeId,
      Long id) {
    return promotionDetailsRepository.existsByPromotedDesignationIdAndEmployeeIdIgnoreCaseAndIdNot(
        designationId, employeeId, id);
  }

}
