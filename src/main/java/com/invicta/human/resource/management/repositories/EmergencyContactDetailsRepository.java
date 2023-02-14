package com.invicta.human.resource.management.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.invicta.human.resource.management.dto.EmergencyContactDetailsIdDto;
import com.invicta.human.resource.management.entities.EmergencyContactDetails;

public interface EmergencyContactDetailsRepository
    extends JpaRepository<EmergencyContactDetails, Long> {

  List<EmergencyContactDetailsIdDto> findByEmployeeId(String id);

  void deleteByEmployeeId(String id);

  boolean existsByEmployeeIdIgnoreCase(String employeeId);

  boolean existsByPhoneNumber(String mobileNumber);

  boolean existsByPhoneNumberAndEmployeeId(String phoneNumber, String employeeId);

  boolean existsByPhoneNumberAndEmployeeIdAndIdNot(String phoneNumber, String employeeId, long id);

}
