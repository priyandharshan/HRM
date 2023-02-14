package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class LaptopAllocationDetails extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String serialNumber;
  private String previousOwner;
  private boolean charger;
  private boolean networkCable;
  private Date assignedDate;
  @Column(length = 500)
  private String usingLocation;
  @Column(length = 1000)
  private String remarks;
  private String assetTagName;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
