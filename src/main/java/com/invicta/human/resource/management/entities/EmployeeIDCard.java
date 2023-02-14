package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee_id_card")
@Getter
@Setter
public class EmployeeIDCard extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date givenDate;
  private Date handedDate;
  private boolean lost;
  private Date lostDate;
  private boolean complaintCopy;
  private Date informDate;
  private boolean policeComplaint;
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
