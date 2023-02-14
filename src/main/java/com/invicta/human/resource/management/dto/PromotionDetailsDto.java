package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PromotionDetailsDto {

  private Long id;
  private String employeeId;
  private Date promotionDate;
  private Long designationId;
}
