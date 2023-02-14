package com.invicta.human.resource.management.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invicta.human.resource.management.entities.InductionCheckList;

@Repository
public interface InductionCheckListRepository
    extends JpaRepository<InductionCheckList, Long> {

  InductionCheckList findByEmployeeId(String employeeId);

  boolean existsByEmployeeId(String employeeId);

  List<InductionCheckList> findAllByEmployeeId(String employeeId);

}
