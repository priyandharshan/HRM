package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.PromotionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionDetailsRepository extends JpaRepository<PromotionDetails, Long> {

  List<PromotionDetails> findAllByEmployeeId(String id);

  boolean existsByPromotedDesignationIdAndEmployeeIdIgnoreCase(Long designationId,
      String employeeId);

  boolean existsByPromotedDesignationIdAndEmployeeIdIgnoreCaseAndIdNot(Long designationId,
      String employeeId, Long id);
}
