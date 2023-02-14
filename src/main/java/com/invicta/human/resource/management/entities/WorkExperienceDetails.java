package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "work_experience_details")
public class WorkExperienceDetails extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String companyName;
  private String experienceDesignation;
  private Date startDate;
  private Date finishDate;
  @Column(length = 1000)
  private String reasonForLeaving;
  @Column(length = 1000)
  private String description;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;
  @OneToMany(mappedBy = "workExperienceDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<WorkedProject> workedProjects;

}
