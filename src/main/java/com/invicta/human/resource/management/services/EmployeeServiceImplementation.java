package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.*;
import com.invicta.human.resource.management.entities.*;
import com.invicta.human.resource.management.repositories.EmployeeRepository;
import com.invicta.human.resource.management.repositories.UserCredentialsRepository;
import com.invicta.human.resource.management.response.PaginatedContentResponse;
import com.invicta.human.resource.management.searchdto.SearchEmployeeDto;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.PropertyFileValue;
import com.invicta.human.resource.management.utils.SearchField;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private WorkedProjectsService workedProjectsService;
  @Autowired
  private EmployeeProfileImageService employeeProfileImageService;
  @Autowired
  private UserCredentialsRepository userCredentialsRepository;
  @Autowired
  private PropertyFileValue propertyFileValue;
  @Autowired
  private RestTemplate restTemplate;

  // Implemented By : Dhivyaruban
  // Implemented On : 21-10-2022
  // Implemented For : Add an employee
  @Override
  public void saveEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  // Implemented By : Dhivyaruban
  // Implemented On : 22-10-2022
  // Implemented For : Read an employee
  @Override
  public ProfileDetailsDto getProfileDetails(String id) {
    Employee employee = employeeRepository.findById(id).get();

    ProfileDetailsDto profileDetailsDto = new ProfileDetailsDto();
    ResponseEmployeeDto responseEmployeeDto = new ResponseEmployeeDto();
    PersonalDetailsDto personalDetailsDto = new PersonalDetailsDto();
    ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
    List<EmergencyContactDetailsDto> emergencyContactDetailsDtoList = new ArrayList<>();
    List<ParDetailsDto> parDetailsDtoList = new ArrayList<>();
    List<ResponsePromotionDetailsDto> responsePromotionDetailsDtoList = new ArrayList<>();
    EmployeeProfileImageDto employeeProfileImageDto = new EmployeeProfileImageDto();
    InductionCheckListDto inductionCheckListDto = new InductionCheckListDto();
    List<RefereesDetailsDto> refereesDetailsDtoList = new ArrayList<>();
    List<VaccinationDetailsDto> vaccinationDetailsDtoList = new ArrayList<>();
    List<BCardDetailsDto> bCardDetailsDtoList = new ArrayList<>();
    LaptopAllocationDetailsDto laptopAllocationDetailsDto = new LaptopAllocationDetailsDto();
    EmployeeIDCardDto employeeIDCardDto = new EmployeeIDCardDto();
    ResignationDto resignationDto = new ResignationDto();
    ExitProcedureDto exitProcedureDto = new ExitProcedureDto();
    KeyRegistryDto keyRegistryDto = new KeyRegistryDto();
    List<ProfessionalCertificatesDto> professionalCertificatesDtoList = new ArrayList<>();
    List<WorkedProjectDto> workedProjectDtoList = new ArrayList<>();
    List<ResponseWorkExperienceDetailsDto> responseWorkExperienceDetailsDtoList = new ArrayList<>();
    List<EducationQualificationDto> educationQualificationDtoList = new ArrayList<>();

    DesignationDto designationDto = new DesignationDto();
    BeanUtils.copyProperties(employee.getDesignation(), designationDto);
    BeanUtils.copyProperties(employee, responseEmployeeDto);
    responseEmployeeDto.setDesignationDto(designationDto);

    EmploymentCategoryDto employmentCategoryDto = new EmploymentCategoryDto();
    if (employee.getEmploymentCategory() != null) {
      BeanUtils.copyProperties(employee.getEmploymentCategory(), employmentCategoryDto);
      responseEmployeeDto.setEmploymentCategoryDto(employmentCategoryDto);
    }
    responseEmployeeDto.setTitle(employee.getTitle());

    if (employee.getJobTitle() != null) {
      JobTitleDto jobTitleDto =
          new JobTitleDto(employee.getJobTitle().getId(), employee.getJobTitle().getJobTitle());
      responseEmployeeDto.setJobTitleDto(jobTitleDto);
    }


    if (employee.getPersonalDetails() != null) {
      BeanUtils.copyProperties(employee.getPersonalDetails(), personalDetailsDto);
      personalDetailsDto.setMaritalStatus(employee.getPersonalDetails().getMaritalStatus());
    }

    if (employee.getContactDetails() != null) {
      BeanUtils.copyProperties(employee.getContactDetails(), contactDetailsDto);
    }

    if (!employee.getEmergencyContactDetailsList().isEmpty()) {
      List<EmergencyContactDetails> emergencyContactDetailsList =
          employee.getEmergencyContactDetailsList();

      for (EmergencyContactDetails emergencyContactDetails : emergencyContactDetailsList) {
        EmergencyContactDetailsDto emergencyContactDetailsDto = new EmergencyContactDetailsDto();
        BeanUtils.copyProperties(emergencyContactDetails, emergencyContactDetailsDto);
        emergencyContactDetailsDtoList.add(emergencyContactDetailsDto);
      }
    }

    if (!employee.getParDetailsList().isEmpty()) {
      List<ParDetails> parDetailsList = employee.getParDetailsList().stream()
          .sorted(Comparator.comparing(ParDetails::getReviewedDate).reversed())
          .collect(Collectors.toList());

      int parDisplayLimit = 2;

      for (int i = 0; i < parDetailsList.size(); i++) {
        if (i == parDisplayLimit) {
          break;
        }
        ParDetailsDto parDetailsDto = new ParDetailsDto();
        BeanUtils.copyProperties(parDetailsList.get(i), parDetailsDto);
        parDetailsDtoList.add(parDetailsDto);
      }
    }

    if (!employee.getPromotionDetailsList().isEmpty()) {
      List<PromotionDetails> promotionDetailsList = employee.getPromotionDetailsList().stream()
          .sorted(Comparator.comparing(PromotionDetails::getPromotionDate).reversed())
          .collect(Collectors.toList());

      for (PromotionDetails promotionDetails : promotionDetailsList) {
        ResponsePromotionDetailsDto responsePromotionDetailsDto = new ResponsePromotionDetailsDto();
        BeanUtils.copyProperties(promotionDetails, responsePromotionDetailsDto);
        responsePromotionDetailsDto.setPromotedDesignationId(
            promotionDetails.getPromotedDesignation().getId());
        responsePromotionDetailsDto.setPromotedDesignation(
            promotionDetails.getPromotedDesignation().getDesignation());
        responsePromotionDetailsDtoList.add(responsePromotionDetailsDto);
      }
    }

    if (employee.getEmployeeProfileImage() != null) {
      BeanUtils.copyProperties(employee.getEmployeeProfileImage(), employeeProfileImageDto);
    }

    if (employee.getInductionCheckList() != null) {
      BeanUtils.copyProperties(employee.getInductionCheckList(), inductionCheckListDto);
    }

    if (!employee.getRefereesDetailsList().isEmpty()) {
      List<RefereesDetails> refereesDetailsList = employee.getRefereesDetailsList();

      for (RefereesDetails refereesDetails : refereesDetailsList) {
        RefereesDetailsDto refereesDetailsDto = new RefereesDetailsDto();
        BeanUtils.copyProperties(refereesDetails, refereesDetailsDto);
        refereesDetailsDtoList.add(refereesDetailsDto);
      }
    }

    if (!employee.getVaccinationDetailsList().isEmpty()) {
      List<VaccinationDetails> vaccinationDetailsList = employee.getVaccinationDetailsList();

      for (VaccinationDetails vaccinationDetails : vaccinationDetailsList) {
        VaccinationDetailsDto vaccinationDetailsDto = new VaccinationDetailsDto();
        BeanUtils.copyProperties(vaccinationDetails, vaccinationDetailsDto);
        vaccinationDetailsDtoList.add(vaccinationDetailsDto);
      }
    }

    if (!employee.getBCardDetailsList().isEmpty()) {
      List<BCardDetails> bCardDetailsList = employee.getBCardDetailsList();

      for (BCardDetails bCardDetails : bCardDetailsList) {
        BCardDetailsDto bCardDetailsDto = new BCardDetailsDto();
        BeanUtils.copyProperties(bCardDetails, bCardDetailsDto);
        bCardDetailsDtoList.add(bCardDetailsDto);
      }
    }

    if (employee.getLaptopAllocationDetails() != null) {
      BeanUtils.copyProperties(employee.getLaptopAllocationDetails(), laptopAllocationDetailsDto);
    }

    if (employee.getEmployeeIDCard() != null) {
      BeanUtils.copyProperties(employee.getEmployeeIDCard(), employeeIDCardDto);
    }

    if (employee.getResignation() != null) {
      BeanUtils.copyProperties(employee.getResignation(), resignationDto);
    }

    if (employee.getExitProcedure() != null) {
      BeanUtils.copyProperties(employee.getExitProcedure(), exitProcedureDto);
    }

    if (employee.getKeyRegistry() != null) {
      BeanUtils.copyProperties(employee.getKeyRegistry(), keyRegistryDto);
    }

    if (!employee.getProfessionalCertificatesList().isEmpty()) {
      List<ProfessionalCertificates> professionalCertificatesList =
          employee.getProfessionalCertificatesList();

      for (ProfessionalCertificates professionalCertificates : professionalCertificatesList) {
        ProfessionalCertificatesDto professionalCertificatesDto = new ProfessionalCertificatesDto();
        BeanUtils.copyProperties(professionalCertificates, professionalCertificatesDto);
        professionalCertificatesDtoList.add(professionalCertificatesDto);
      }
    }

    if (!employee.getWorkExperienceDetailsList().isEmpty()) {
      List<WorkExperienceDetails> workExperienceDetailsList =
          employee.getWorkExperienceDetailsList();

      for (WorkExperienceDetails workExperienceDetails : workExperienceDetailsList) {
        ResponseWorkExperienceDetailsDto responseWorkExperienceDetailsDto =
            new ResponseWorkExperienceDetailsDto();
        BeanUtils.copyProperties(workExperienceDetails, responseWorkExperienceDetailsDto);

        workedProjectDtoList = workedProjectsService.getAllProjectByWorkExperienceDetailsId(
            workExperienceDetails.getId());

        responseWorkExperienceDetailsDto.setWorkedProjects(workedProjectDtoList);
        responseWorkExperienceDetailsDtoList.add(responseWorkExperienceDetailsDto);
      }
    }

    if (!employee.getEducationQualificationsList().isEmpty()) {
      List<EducationQualifications> educationQualificationsList =
          employee.getEducationQualificationsList();

      for (EducationQualifications educationQualifications : educationQualificationsList) {
        EducationQualificationDto educationQualificationDto = new EducationQualificationDto();
        BeanUtils.copyProperties(educationQualifications, educationQualificationDto);
        educationQualificationDto.setInstituteName(
            educationQualifications.getInstitutions().getInstituteName());
        educationQualificationDtoList.add(educationQualificationDto);
      }
    }

    // Setting up response complete details dto
    profileDetailsDto.setResponseEmployeeDto(responseEmployeeDto);
    profileDetailsDto.setPersonalDetailsDto(personalDetailsDto);
    profileDetailsDto.setContactDetailsDto(contactDetailsDto);
    profileDetailsDto.setEmergencyContactDetailsDtoList(emergencyContactDetailsDtoList);
    profileDetailsDto.setParDetailsDtoList(parDetailsDtoList);
    profileDetailsDto.setResponsePromotionDetailsDtoList(responsePromotionDetailsDtoList);
    profileDetailsDto.setEmployeeProfileImageDto(employeeProfileImageDto);
    profileDetailsDto.setInductionCheckListDto(inductionCheckListDto);
    profileDetailsDto.setRefereesDetailsDtoList(refereesDetailsDtoList);
    profileDetailsDto.setVaccinationDetailsDtoList(vaccinationDetailsDtoList);
    profileDetailsDto.setBCardDetailsDtoList(bCardDetailsDtoList);
    profileDetailsDto.setLaptopAllocationDetailsDto(laptopAllocationDetailsDto);
    profileDetailsDto.setEmployeeIDCardDto(employeeIDCardDto);
    profileDetailsDto.setResignationDto(resignationDto);
    profileDetailsDto.setExitProcedureDto(exitProcedureDto);
    profileDetailsDto.setKeyRegistryDto(keyRegistryDto);
    profileDetailsDto.setProfessionalCertificatesDtoList(professionalCertificatesDtoList);
    profileDetailsDto.setResponseWorkExperienceDetailsDtoList(responseWorkExperienceDetailsDtoList);
    profileDetailsDto.setEducationQualificationDtoList(educationQualificationDtoList);

    return profileDetailsDto;
  }

  // Implemented By : Dhivyaruban
  // Implemented On : 23-10-2022
  // Implemented For : Update employee details
  @Override
  public void updateEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  @Transactional
  public void deleteEmployee(String id) {
    if (employeeProfileImageService.existsByEmployeeId(id)) {
      employeeProfileImageService.deleteImage(id);
    }
    employeeRepository.deleteById(id);
  }

  @Override
  public boolean existsByEmployeeId(String id) {
    return employeeRepository.existsById(id);
  }

  @Override
  public List<EmployeeForMainTableDto> getEmployees() {
    List<EmployeeForMainTableDto> employeeForMainTableDtoList = new ArrayList<>();
    List<Employee> employeeList = employeeRepository.findByOrderByIdAsc();

    for (Employee employee : employeeList) {
      EmployeeForMainTableDto employeeForMainTableDto = new EmployeeForMainTableDto();
      DesignationDto designationDto = new DesignationDto();
      BeanUtils.copyProperties(employee.getDesignation(), designationDto);

      employeeForMainTableDto.setDesignationDto(designationDto);
      employeeForMainTableDto.setEmployeeID(employee.getId());
      employeeForMainTableDto.setFirstName(employee.getFirstName());
      employeeForMainTableDto.setSurname(employee.getLastName());

      if (employee.getContactDetails() != null) {
        employeeForMainTableDto.setEmail(employee.getContactDetails().getEmail());
      }
      employeeForMainTableDtoList.add(employeeForMainTableDto);
    }

    return employeeForMainTableDtoList;
  }

  @Override
  public Employee getEmployeeDetails(String employeeId) {
    return employeeRepository.findById(employeeId).get();
  }

  @Override
  public void updateListEmployee(List<Employee> employeeList) {
    employeeRepository.saveAll(employeeList);
  }

  @Override
  public List<Employee> findAllEmployee() {
    return employeeRepository.findAllByFirstNameIsNot(Constants.ADMIN_ROLE);
  }

  /**
   * Check Designation Id exists or not
   *
   * @param id
   * @author Thenuga
   * @date 24-11-2022
   */

  @Override
  public boolean existsByDesignationId(Long id) {
    return employeeRepository.existsByDesignationId(id);
  }

  @Override
  public List<EmployeeDetailsDropDownDto> getAllEmployeeDetailsDropDownList() {
    List<EmployeeDetailsDropDownDto> employeeDetailsDropDownDtoList = new ArrayList<>();
    List<Employee> employeeList = findAllEmployee();
    for (Employee employee : employeeList) {
      if (userCredentialsRepository.existsByEmployeeId(
          employee.getId()) && employee.getUserCredentials().isActive()) {
        EmployeeDetailsDropDownDto employeeDetailsDropDownDto = new EmployeeDetailsDropDownDto();
        BeanUtils.copyProperties(employee, employeeDetailsDropDownDto);
        employeeDetailsDropDownDto.setDesignation(employee.getDesignation().getDesignation());
        employeeDetailsDropDownDto.setEmployeeId(employee.getId());
        employeeDetailsDropDownDto.setActive(employee.getUserCredentials().isActive());
        employeeDetailsDropDownDtoList.add(employeeDetailsDropDownDto);
      }
    }
    return employeeDetailsDropDownDtoList;
  }

  @Override
  public boolean existsByEmploymentCategoryId(Long id) {
    return employeeRepository.existsByEmploymentCategoryId(id);
  }

  /**
   * @param id Job title id
   * @return true if job title id exists in employee table
   */
  @Override
  public boolean existsByJobTitle(Long id) {
    return employeeRepository.existsByJobTitle_Id(id);
  }

  @Override
  public List<EmployeeForMainTableDto> searchEmployeePagination(SearchEmployeeDto searchEmployeeDto,
      Pageable pageable, PaginatedContentResponse.Pagination pagination) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    List<EmployeeForMainTableDto> employeeForMainTableDtoList = new ArrayList<>();
    QEmployee employee = QEmployee.employee;
    if (!SearchField.isNullOrEmpty(searchEmployeeDto.getId())) {
      booleanBuilder.and(QEmployee.employee.id.containsIgnoreCase(searchEmployeeDto.getId()));
    }
    if (!SearchField.isNullOrEmpty(searchEmployeeDto.getFirstName())) {
      booleanBuilder.and(
          QEmployee.employee.firstName.containsIgnoreCase(searchEmployeeDto.getFirstName()));
    }
    if (!SearchField.isNullOrEmpty(searchEmployeeDto.getSurname())) {
      booleanBuilder.and(
          QEmployee.employee.lastName.containsIgnoreCase(searchEmployeeDto.getSurname()));
    }
    if (!SearchField.isNullOrEmpty(searchEmployeeDto.getDesignation())) {
      booleanBuilder.and(QEmployee.employee.designation.designation.containsIgnoreCase(
          searchEmployeeDto.getDesignation()));
    }
    if (!SearchField.isNullOrEmpty(searchEmployeeDto.getEmploymentCategory())) {
      booleanBuilder.and(
          QEmployee.employee.employmentCategory.employmentCategory.containsIgnoreCase(
              searchEmployeeDto.getEmploymentCategory()));
    }
    if (!SearchField.isNullOrEmpty(searchEmployeeDto.getEmail())) {
      booleanBuilder.and(
          QEmployee.employee.contactDetails.email.containsIgnoreCase(searchEmployeeDto.getEmail()));
    }
    booleanBuilder.and(QEmployee.employee.firstName.ne(Constants.ADMIN_ROLE));
    Page<Employee> employeePage = employeeRepository.findAll(booleanBuilder, pageable);
    List<Employee> employeeList = employeePage.getContent();
    pagination.setTotalPages(employeePage.getTotalPages());
    pagination.setTotalRecords(employeePage.getTotalElements());
    for (Employee employeeObject : employeeList) {
      EmployeeForMainTableDto employeeForMainTableDto = new EmployeeForMainTableDto();
      DesignationDto designationDto = new DesignationDto();
      BeanUtils.copyProperties(employeeObject, employeeForMainTableDto);
      employeeForMainTableDto.setEmployeeID(employeeObject.getId());
      employeeForMainTableDto.setSurname(employeeObject.getLastName());
      if (employeeObject.getContactDetails() != null) {
        employeeForMainTableDto.setEmail(employeeObject.getContactDetails().getEmail());
      }
      BeanUtils.copyProperties(employeeObject.getDesignation(), designationDto);
      employeeForMainTableDto.setDesignationDto(designationDto);
      EmployeeCategoryDto employeeCategoryDto = new EmployeeCategoryDto();
      if (employeeObject.getEmploymentCategory() != null) {
        BeanUtils.copyProperties(employeeObject.getEmploymentCategory(), employeeCategoryDto);
        employeeForMainTableDto.setEmployeeCategoryDto(employeeCategoryDto);
      }
      employeeForMainTableDtoList.add(employeeForMainTableDto);
    }
    return employeeForMainTableDtoList;
  }

  @Override
  public List<EmployeeMainTableResponseDto> getAllEmployees() {
    List<EmployeeMainTableResponseDto> employeeMainTableResponseDtoList = new ArrayList<>();
    List<Employee> employeeList = findAllEmployee();
    for (Employee employee : employeeList) {
      EmployeeMainTableResponseDto employeeMainTableResponseDto =
          new EmployeeMainTableResponseDto();
      BeanUtils.copyProperties(employee, employeeMainTableResponseDto);
      employeeMainTableResponseDtoList.add(employeeMainTableResponseDto);
    }
    List<EmployeeMainTableResponseDto> sortedList = employeeMainTableResponseDtoList.stream()
        .sorted(Comparator.comparing(EmployeeMainTableResponseDto::getFirstName))
        .collect(Collectors.toList());
    return sortedList;
  }

  @Override
  public ProfileViewDto getProfileView(String employeeId) {
    ProfileViewDto profileViewDto = new ProfileViewDto();
    Employee employee = employeeRepository.findById(employeeId).get();
    BeanUtils.copyProperties(employee, profileViewDto);
    profileViewDto.setDesignation(employee.getDesignation().getDesignation());
    if (employee.getEmployeeProfileImage() != null) {
      profileViewDto.setImageName(employee.getEmployeeProfileImage().getFileName());
    }
    return profileViewDto;
  }

  @Override
  public ResponseEntity createLeaveManagement(LeaveManagementDto leaveManagementDto) {
    HttpHeaders httpHeaders = new HttpHeaders();
    HttpEntity httpEntity = new HttpEntity(leaveManagementDto, httpHeaders);
    return restTemplate.exchange(propertyFileValue.getLeaveManagementUrl(), HttpMethod.POST,
        httpEntity, String.class);
  }
}
