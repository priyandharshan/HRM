package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "par_details")
public class ParDetails extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Size(max = 100, message = "Should be between 0 and 100")
  private int performanceRating;
  @Size(max = 100, message = "Should be between 0 and 100")
  private int communicationRating;
  @Size(max = 100, message = "Should be between 0 and 100")
  private int technicalRating;
  @Past
  private Date reviewedDate;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
