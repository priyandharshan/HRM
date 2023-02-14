package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.*;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.response.PaginatedContentResponse;
import com.invicta.human.resource.management.searchdto.SearchEmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

  void saveEmployee(Employee employee);

  ProfileDetailsDto getProfileDetails(String id);

  void updateEmployee(Employee employee);

  void deleteEmployee(String id);

  boolean existsByEmployeeId(String id);

  List<EmployeeForMainTableDto> getEmployees();

  Employee getEmployeeDetails(String employeeId);

  boolean existsByDesignationId(Long id);

  void updateListEmployee(List<Employee> employeeList);

  List<Employee> findAllEmployee();

  List<EmployeeDetailsDropDownDto> getAllEmployeeDetailsDropDownList();

  boolean existsByEmploymentCategoryId(Long id);

  boolean existsByJobTitle(Long id);

  List<EmployeeForMainTableDto> searchEmployeePagination(SearchEmployeeDto searchEmployeeDto,
      Pageable pageable, PaginatedContentResponse.Pagination pagination);

  List<EmployeeMainTableResponseDto> getAllEmployees();

  ProfileViewDto getProfileView(String employeeId);

  ResponseEntity createLeaveManagement(LeaveManagementDto leaveManagementDto);
}
