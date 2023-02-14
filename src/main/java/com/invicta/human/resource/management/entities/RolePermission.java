package com.invicta.human.resource.management.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role_permission")
@Getter
@Setter
public class RolePermission extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_PERMISSION")
  @SequenceGenerator(name = "ROLE_PERMISSION", sequenceName = "SEQ_ROLE_PERMISSION",
      allocationSize = 1)
  private Long id;
  private boolean status;
  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Designation designation;
  @ManyToOne
  @JoinColumn(name = "permission_id", nullable = false)
  private Permission permission;
}
