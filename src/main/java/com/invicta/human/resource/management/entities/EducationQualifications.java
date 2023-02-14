package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "education_qualification")
public class EducationQualifications extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date educationQualificationStartDate;
  private Date educationQualificationFinishDate;
  @Column(length = 1000)
  private String educationQualificationDescription;
  private String degree;
  private String grade;
  @ManyToOne(optional = false)
  @JoinColumn(name = "institution_id", nullable = false)
  private Institutions institutions;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
