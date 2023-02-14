package com.invicta.human.resource.management.repositories;

import com.invicta.human.resource.management.entities.Designation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesignationRepository
		extends JpaRepository<Designation, Long>, QuerydslPredicateExecutor<Designation> {

	boolean existsByDesignationIgnoreCase(String designation);

	Optional<Designation> findById(Long id);

	void deleteById(Long id);

	boolean existsByDesignationIgnoreCaseAndIdNot(String designation, long id);

  List<Designation> findAllByDesignationIsNotOrderByDesignationAsc(String admin);
}
