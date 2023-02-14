package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

  Optional<ContactDetails> findByEmployeeId(String id);

  void deleteByEmployeeId(String id);

  boolean existsByEmailIgnoreCase(String email);

  boolean existsByMobileNumber(String mobileNumber);

  boolean existsByEmailIgnoreCaseAndEmployeeIdIgnoreCaseNot(String email, String id);

  boolean existsByMobileNumberAndEmployeeIdIgnoreCaseNot(String mobileNumber, String id);

  ContactDetails findByEmailIgnoreCase(String email);

  ContactDetails findByMobileNumber(String mobileNumber);

  boolean existsByEmployeeIdIgnoreCase(String employeeId);

  boolean existsByOfficialEmailIgnoreCase(String officialEmail);

  boolean existsByOfficialEmailIgnoreCaseAndEmployeeIdIgnoreCaseNot(String officialEmail,
      String id);


}
