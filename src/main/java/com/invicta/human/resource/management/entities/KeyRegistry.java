package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class KeyRegistry extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String keyNumber;
  private boolean keyAvailable;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
