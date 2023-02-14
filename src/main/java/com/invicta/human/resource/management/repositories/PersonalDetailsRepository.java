package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {

  Optional<PersonalDetails> findByEmployeeId(String id);

  void deleteByEmployeeId(String id);

  boolean existsByNicNumberIgnoreCase(String nicNumber);

  boolean existsByNicNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(String nicNumber, String id);

  boolean existsByEmployeeIdIgnoreCase(String employeeId);

  PersonalDetails findByNicNumber(String nicNumber);

  List<PersonalDetails> findAllByEmployeeId(String employeeId);

  boolean existsByPassportNumberIgnoreCase(String passportNumber);

  boolean existsByPassportNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(String passportNumber, String employeeId);

  boolean existsByDrivingLicenseNumberIgnoreCase(String drivingLicenceNumber);

  boolean existsByDrivingLicenseNumberIgnoreCaseAndEmployeeIdIgnoreCaseNot(String drivingLicenseNumber, String employeeId);
}
