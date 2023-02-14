package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class BCardDetails extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long epfNumber;
  private Date createdDate;
  private Date courieredDate;
  private Boolean isApproval;
  @Column(length=500)
  private String approvalRejectedReason;
  private Date receivedDate;
  private Boolean handedOver;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

}
