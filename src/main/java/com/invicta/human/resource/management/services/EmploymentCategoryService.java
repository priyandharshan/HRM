package com.invicta.human.resource.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.invicta.human.resource.management.entities.EmploymentCategory;

@Service
public interface EmploymentCategoryService {

	boolean isEmploymentCategoryExists(String employmentCategory);

	void saveEmploymentCategory(EmploymentCategory employmentCategory);

	List<EmploymentCategory> getAllEmploymentCategory();

	boolean existsByEmploymentCategoryId(long employmentCategoryId);

	Object getBYId(Long id);

	void deleteEmploymentCategory(Long id);

	void updateEmploymentCategory(EmploymentCategory employmentCategory);

	boolean updateEmploymentCategory(String employmentCategory, long id);

}
