package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>,
		QuerydslPredicateExecutor<Employee> {

	boolean existsByDesignationId(Long id);

	List<Employee> findByOrderByIdAsc();

	boolean existsByEmploymentCategoryId(Long id);

	boolean existsByJobTitle_Id(Long id);

	List<Employee> findAllByFirstNameIsNot(String admin);
}
