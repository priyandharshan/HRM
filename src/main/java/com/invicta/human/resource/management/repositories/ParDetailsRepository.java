package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.ParDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParDetailsRepository extends JpaRepository<ParDetails, Long> {

  Optional<ParDetails> findByEmployeeId(String id);
}
