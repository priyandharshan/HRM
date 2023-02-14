package com.invicta.human.resource.management.utils;

public class EndPointURI {
  public static final String SEARCH_PAGINATION = "/search-pagination";
  // Employee
  public static final String ADD_EMPLOYEE = "/employee";
  public static final String EMPLOYEES = "/employees";
  public static final String EMPLOYEE = "/employees/{id}";
  public static final String VERIFY = "/email/verify";
  public static final String EMPLOYEE_ID = "{employeeId}";
  public static final String SEARCH_EMPLOYEE = "/search";
  public static final String EMPLOYEE_MAIN = "/employee-main";

  // Designation
  public static final String ID = "{id}";
  public static final String SLASH = "/";
  public static final String DESIGNATION = "/designation";
  public static final String DESIGNATIONBYID = DESIGNATION + SLASH + ID;
  public static final String DESIGNATION_SEARCH_PAGINATION = DESIGNATION + SEARCH_PAGINATION;
  // Personal details
  public static final String ADD_PERSONAL_DETAILS = "/personal-details";
  public static final String UPDATE_PERSONAL_DETAILS = "/personal-details-list";
  public static final String EMPLOYEE_DETAILS_DROP_DOWN = "/employeeDetailsDropDown";

  // InductionCheckList
  public static final String ADD_INDUCTIONCHECKLIST = "/inductionChecklist";
  public static final String INDUCTIONCHECKLIST_BY_EMPLOYEE_ID =
      ADD_INDUCTIONCHECKLIST + SLASH + EMPLOYEE_ID;

  // ExitProcedure
  public static final String ADD_EXIT_PROCEDURE = "/exitProcedure";
  public static final String EXIT_PROCEDURE_BY_EMPLOYEE_ID =
      ADD_EXIT_PROCEDURE + SLASH + EMPLOYEE_ID;

  // KeyRegistry
  public static final String KEY_REGISTRY = "/keyRegistry";
  public static final String KEY_REGISTRY_BY_EMPLOYEE_ID = KEY_REGISTRY + SLASH + EMPLOYEE_ID;

  // Resignation
  public static final String RESIGNATION = "/resignation";
  public static final String RESIGNATION_BY_EMPLOYEE_ID = RESIGNATION + SLASH + EMPLOYEE_ID;

  // Laptop Allocation
  public static final String LAPTOP_ALLOCATION = "/laptopAllocation";
  public static final String LAPTOP_ALLOCATION_BY_EMPLOYEE_ID =
      LAPTOP_ALLOCATION + SLASH + EMPLOYEE_ID;

  // Employee ID Card
  public static final String EMPLOYEE_ID_CARD = "/employeeIdCard";
  public static final String EMPLOYEE_ID_CARD_BY_EMPLOYEE_ID =
      EMPLOYEE_ID_CARD + SLASH + EMPLOYEE_ID;

  // B-Card
  public static final String B_CARD = "/bCard";
  public static final String B_CARD_BY_ID = B_CARD + SLASH + ID;
  public static final String B_CARD_DETAILS_BY_EMPLOYEE_ID = B_CARD + SLASH + EMPLOYEE_ID;

  // Images
  public static final String IMAGE = "/employees/image/{id}";
  public static final String PROFILE_IMAGE="/profile-image/";
  public static final String GET_IMAGE = PROFILE_IMAGE+"{id}/{filename}";

  // Contact details
  public static final String ADD_CONTACT_DETAILS = "/contact-details";
  public static final String UPDATE_CONTACT_DETAILS = "/contact-details-list";

  // Emergency contact details
  public static final String ADD_EMERGENCY_CONTACT_DETAILS = "/emergency-contact-details";
  public static final String UPDATE_EMERGENCY_CONTACT_DETAILS = "/emergency-contact-details-list";
  public static final String EMERGENCY_CONTACT_DETAILS =
      "/emergency-contact-details-list/{employeeId}";

  public static final String EMERGENCY_CONTACT_DETAILS_BY_ID =
      "/emergency-contact-details-list/{id}";

  // Promotion details
  public static final String ADD_PROMOTION_DETAILS = "/promotion-details";
  public static final String PROMOTION_DETAILS_LIST = "/promotion-details-list";
  public static final String PROMOTION_DETAILS = "/promotion-details-list/{id}";

  // PAR details
  public static final String ADD_PAR_DETAILS = "/par-details";
  public static final String PAR_DETAILS_LIST = "/par-details-list";
  public static final String PAR_DETAILS = "/par-details-list/{id}";

  // Vaccination Details
  public static final String ADD_VACCINATION_DETAILS = "/vaccine-details";
  public static final String VACCINATION_DETAILS_BY_EMPLOYEE_ID = "/vaccine-details/{employeeId}";
  public static final String VACCINATION_DETAILS_BY_ID = "/vaccine-details/{id}";

  // Referees Details
  public static final String ADD_REFEREES_DETAILS = "/referees-details";
  public static final String REFEREES_DETAILS_BY_EMPLOYEE_ID = "/referees-details/{employeeId}";
  public static final String REFEREES_DETAILS_BY_ID = "/referees-details/{id}";

  // Professional Certificates
  public static final String PROFESSIONAL_CERTIFICATES = "/professionalCertificates";
  public static final String PROFESSIONAL_CERTIFICATES_BY_ID =
      PROFESSIONAL_CERTIFICATES + SLASH + ID;
  public static final String PROFESSIONAL_CERTIFICATES_BY_EMPLOYEE_ID =
      PROFESSIONAL_CERTIFICATES + SLASH + EMPLOYEE_ID;

  // Worked projects
  public static final String ADD_WORKED_PROJECTS = "/projects";
  public static final String WORKED_PROJECTS_BY_WORK_EXPERIENCE_DETAILS_ID =
      "/projects/{workExperienceDetailsId}";
  public static final String WORKED_PROJECTS_BY_ID = "/projects/{id}";

  // Work Experience
  public static final String ADD_WORK_EXPERIENCE_DETAILS = "/work-experience";
  public static final String WORK_EXPERIENCE_DETAILS_BY_EMPLOYEE_ID =
      "/work-experience/{employeeId}";
  public static final String WORK_EXPERIENCE_DETAILS_BY_ID = "/work-experience/{id}";

  // Education and Qualifications
  public static final String ADD_EDUCATION_QUALIFICATION = "/education-qualification";
  public static final String EDUCATION_QUALIFICATION_BY_EMPLOYEE_ID =
      "/education-qualification/{employeeId}";
  public static final String EDUCATION_QUALIFICATION_BY_ID = "/education-qualification/{id}";

  // Institutes
  public static final String GET_ALL_INSTITUTES = "/institutes";

  // Defect Tracker System
  public static final String DEFECT_TRACKER_SYSTEM = "/defect-tracker";

  // Employment Category
  public static final String EMPLOYMENTCATEGORY = "/employmentCategory";
  public static final String EMPLOYMENTCATEGORYBYID = EMPLOYMENTCATEGORY + SLASH + ID;

  // Job title
  public static final String JOB_TITLE = "/job-titles";
  public static final String JOB_TITLE_BY_ID = "/job-titles/{id}";

  //Change Password
  public static final String CHANGE_PASSWORD = "/password";
  //Role permission
  public static final String ROLE_PERMISSION = "/rolePermission";
  public static final String ROLE_PERMISSION_BY_ROLE = ROLE_PERMISSION + SLASH + ID;
  public static final String SIGN_IN_ROLE_PERMISSION = ROLE_PERMISSION + SLASH + "sign-in";
  public static final String GET_ROLE_PERMISSION ="/role-permission/{id}";

  // permission
  public static final String PERMISSION = "/permission";
  public static final String FORGOT_PASSWORD = "/forgot-password";
  public static final String RESET_PASSWORD = "/reset-password";

  // User Active Status
  public static final String USER_ACTIVE_STATUS = "/user-active-status/{id}";
  public static final String PROFILE_DETAILS = "/profile";
}
