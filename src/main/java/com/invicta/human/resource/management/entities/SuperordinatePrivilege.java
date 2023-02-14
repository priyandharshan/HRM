package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "superordinate_privilege")
public class SuperordinatePrivilege {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPERORDINATE_PRIVILEGE")
  @SequenceGenerator(name = "SUPERORDINATE_PRIVILEGE",
      sequenceName = "SEQ_HR_SUPERORDINATE_PRIVILEGE", allocationSize = 1)
  private Long id;
  private String name;
  @OneToMany(mappedBy = "superordinatePrivilege", fetch = FetchType.EAGER)
  private Set<SubordinatePrivilege> subordinatePrivilege;
}

