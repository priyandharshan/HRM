package com.invicta.human.resource.management.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "worked_projects")
public class WorkedProject extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String projectName;
  @Column(length = 1000)
  private String projectDescription;
  private String role;
  private Date projectStartDate;
  private Date projectFinishDate;
  @Column(length = 1500)
  private String technologyUsed;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "work_experience_id", nullable = false)
  private WorkExperienceDetails workExperienceDetails;

}
