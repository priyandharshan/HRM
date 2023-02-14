package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "permission")
public class Permission extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION")
  @SequenceGenerator(name = "PERMISSION", sequenceName = "SEQ_HR_PERMISSION", allocationSize = 1)
  private long id;
  private String name;
  private String Description;
  @ManyToOne
  @JoinColumn(name = "subordinate_privilege_id", nullable = false)
  private SubordinatePrivilege subordinatePrivilege;
  private boolean status;

}
