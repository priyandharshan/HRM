package com.invicta.human.resource.management.services;

import java.util.List;

import com.invicta.human.resource.management.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invicta.human.resource.management.entities.EmploymentCategory;
import com.invicta.human.resource.management.repositories.EmploymentCategoryRepository;

@Service
public class EmploymentCategoryServiceImplementation implements EmploymentCategoryService {
  @Autowired
  private EmploymentCategoryRepository employmentCategoryRepository;

  /**
   * Check EmploymentCategory Exists Or Not
   *
   * @param employmentCategory
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public boolean isEmploymentCategoryExists(String employmentCategory) {
    return employmentCategoryRepository.existsByEmploymentCategoryIgnoreCase(employmentCategory);
  }

  /**
   * Add Employment Category
   *
   * @param employmentCategory
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public void saveEmploymentCategory(EmploymentCategory employmentCategory) {
    employmentCategoryRepository.save(employmentCategory);

  }

  /**
   * Get all Employment Category
   *
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public List<EmploymentCategory> getAllEmploymentCategory() {
    return employmentCategoryRepository.findAllByEmploymentCategoryIsNotOrderByEmploymentCategoryAsc(
        Constants.ADMIN_ROLE);
  }

  /**
   * Check EmploymentCategory ID Exists Or Not
   *
   * @param employmentCategoryId
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public boolean existsByEmploymentCategoryId(long employmentCategoryId) {
    return employmentCategoryRepository.existsById(employmentCategoryId);
  }

  /**
   * Get Employment Catrgory by Id
   *
   * @param id
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public Object getBYId(Long id) {
    return employmentCategoryRepository.findById(id);
  }

  /**
   * Delete Employment Category
   *
   * @param id
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public void deleteEmploymentCategory(Long id) {
    employmentCategoryRepository.deleteById(id);

  }

  /**
   * Update Employment Category
   *
   * @param employmentCategory
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public void updateEmploymentCategory(EmploymentCategory employmentCategory) {
    employmentCategoryRepository.save(employmentCategory);

  }

  /**
   * Check EmploymentCategory Exists Or Not When Update
   *
   * @param employmentCategory
   * @author Thenuga
   * @date 17-12-2022
   */
  @Override
  public boolean updateEmploymentCategory(String employmentCategory, long id) {
    return employmentCategoryRepository.existsByEmploymentCategoryIgnoreCaseAndIdNot(
        employmentCategory, id);
  }

}
