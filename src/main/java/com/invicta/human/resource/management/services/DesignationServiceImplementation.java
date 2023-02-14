package com.invicta.human.resource.management.services;

import java.util.ArrayList;
import java.util.List;

import com.invicta.human.resource.management.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.invicta.human.resource.management.dto.SearchDesiginationDto;
import com.invicta.human.resource.management.entities.Designation;
import com.invicta.human.resource.management.entities.QDesignation;
import com.invicta.human.resource.management.repositories.DesignationRepository;
import com.invicta.human.resource.management.response.PaginatedContentResponse.Pagination;
import com.invicta.human.resource.management.responseDto.DesiginationResponseDto;
import com.invicta.human.resource.management.utils.SearchField;
import com.querydsl.core.BooleanBuilder;

@Service
public class DesignationServiceImplementation implements DesignationService {
  @Autowired
  private DesignationRepository designationRepository;

  // Implemented By : Thenuga
  // Implemented On : 21-10-2022
  // Implemented For : Add Designation
  @Override
  public void saveDesignation(Designation designation) {
    designationRepository.save(designation);
  }

  // Implemented By : Thenuga
  // Implemented On : 21-10-2022
  // Implemented For : Get all Designation
  @Override
  public List<Designation> getAllDesignation() {
    return designationRepository.findAllByDesignationIsNotOrderByDesignationAsc(Constants.ADMIN_ROLE);
  }

  // Implemented By : Thenuga
  // Implemented On : 21-10-2022
  // Implemented For : Get the Designation by ID
  @Override
  public Object getById(Long id) {
    return designationRepository.findById(id);
  }
  // Implemented By : Thenuga
  // Implemented On : 21-10-2022
  // Implemented For : Delete Designation

  @Transactional
  public void deleteDesignation(Long id) {
    designationRepository.deleteById(id);
  }

  // Implemented By : Thenuga
  // Implemented On : 21-10-2022
  // Implemented For : Check the designation
  @Override
  public boolean isDesignation(String designation) {
    return designationRepository.existsByDesignationIgnoreCase(designation);
  }

  // Implemented By : Thenuga
  // Implemented On : 21-10-2022
  // Implemented For : Update the Designation
  @Override
  public void updateDesignation(Designation designation) {
    designationRepository.save(designation);

  }

  /**
   * @param id - Designaiton id. Must not be null
   * @return the entity by given id
   * @author Dhivyaruban
   * @date 29-10-2022
   */
  @Override
  public boolean existsByDesignationId(Long id) {
    return designationRepository.existsById(id);
  }

  @Override
  public List<DesiginationResponseDto> searchDesiginationPagination(
      SearchDesiginationDto searchDesiginationDto, Pageable pageable, Pagination pagination) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    List<DesiginationResponseDto> desiginationResponseDtoList = new ArrayList<>();
    QDesignation designation = QDesignation.designation1;
    if (!SearchField.isNullOrEmpty(searchDesiginationDto.getName())) {
      booleanBuilder.and(QDesignation.designation1.designation
          .containsIgnoreCase(searchDesiginationDto.getName()));
    }
    Page<Designation> designationPage = designationRepository.findAll(booleanBuilder, pageable);
    List<Designation> designations = designationPage.getContent();
    pagination.setTotalPages(designationPage.getTotalPages());
    pagination.setTotalRecords(designationPage.getTotalElements());
    for (Designation designationObj : designations) {
      DesiginationResponseDto desiginationResponseDto = new DesiginationResponseDto();
      desiginationResponseDto.setName(designationObj.getDesignation());
      BeanUtils.copyProperties(designationObj, desiginationResponseDto);
      desiginationResponseDtoList.add(desiginationResponseDto);
    }
    return desiginationResponseDtoList;
  }

  /**
   * @param id
   * @author Thenuga
   * @date 19-12-2022
   */
  @Override
  public Designation getDesignationById(Long id) {
    return designationRepository.getById(id);
  }

  /**
   * @param designation, id
   * @author Thenuga
   * @date 19-12-2022
   */
  @Override
  public boolean updateDesignationExists(String designation, long id) {
    return designationRepository.existsByDesignationIgnoreCaseAndIdNot(designation, id);
  }

  @Override
  public boolean isIdExists(Long id) {
    return designationRepository.existsById(id);
  }
}
