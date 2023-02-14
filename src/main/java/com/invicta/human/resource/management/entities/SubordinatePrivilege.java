package com.invicta.human.resource.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "subordinate_privilege")
public class SubordinatePrivilege {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBORDINATE_PRIVILEGE")
  @SequenceGenerator(name = "SUBORDINATE_PRIVILEGE", sequenceName = "SEQ_HR_SUBORDINATE_PRIVILEGE",
      allocationSize = 1)
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "superordinate_privilege_id", nullable = false)
  private SuperordinatePrivilege superordinatePrivilege;
  @OneToMany(mappedBy = "subordinatePrivilege", cascade = CascadeType.DETACH,
      fetch = FetchType.EAGER)
  private Set<Permission> permission;
}
