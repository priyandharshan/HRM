package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "promotion_details")
public class PromotionDetails extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date promotionDate;
  @ManyToOne
  @JoinColumn(name = "designation_Id", nullable = false)
  private Designation promotedDesignation;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;
}
