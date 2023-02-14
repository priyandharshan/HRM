package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.Institutions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionsRepository extends JpaRepository<Institutions, Long> {

  boolean existsByInstituteName(String instituteName);

  Institutions findByInstituteName(String instituteName);

}
