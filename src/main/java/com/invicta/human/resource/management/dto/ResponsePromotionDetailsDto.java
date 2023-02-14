package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * A DTO for the {@link com.invicta.human.resource.management.entities.PromotionDetails} entity
 */

@Getter
@Setter
public class ResponsePromotionDetailsDto {

  private Long id;
  private Date promotionDate;
  private Long promotedDesignationId;
  private String promotedDesignation;
  private String employeeId;
}
