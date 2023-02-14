package com.invicta.human.resource.management.entities;

import com.invicta.human.resource.management.enums.VaccinesNames;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vaccination_details")
@Getter
@Setter
public class VaccinationDetails extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private VaccinesNames vaccineType;
  @Column(length = 500)
  private String location;
  private Date vaccineDate;
  private String batchNumber;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;
}
