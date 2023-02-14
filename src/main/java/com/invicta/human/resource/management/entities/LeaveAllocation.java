package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class LeaveAllocation {
  @Id
  private Long id;
  @ManyToOne
  @JoinColumn(name="employee_id")
  private Employee employee;

}
