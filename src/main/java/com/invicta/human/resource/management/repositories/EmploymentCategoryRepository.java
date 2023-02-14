package com.invicta.human.resource.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invicta.human.resource.management.entities.EmploymentCategory;

import java.util.List;

@Repository
public interface EmploymentCategoryRepository extends JpaRepository<EmploymentCategory, Long> {

	boolean existsByEmploymentCategoryIgnoreCase(String employmentCategory);

	boolean existsByEmploymentCategoryIgnoreCaseAndIdNot(String employmentCategory, long id);

	List<EmploymentCategory> findAllByEmploymentCategoryIsNotOrderByEmploymentCategoryAsc(String admin);
}
