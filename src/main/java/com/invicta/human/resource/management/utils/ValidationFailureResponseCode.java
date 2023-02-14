package com.invicta.human.resource.management.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("classpath:validationmessage.properties")
public class ValidationFailureResponseCode {

  // designation validation message
  @Value("${validation.designation.alreadyexists}")
  private String designationAlreadyExists;
  @Value("${validation.designation.notexists}")
  private String designationNotexists;
  @Value("${designation.depend.in.employee.details}")
  private String designationDepend;

  // employee validation message
  @Value("${validation.employeeid.alreadyexits}")
  private String employeeIdAlreadyExists;
  @Value("${validation.employee.alreadyexists}")
  private String employeeEmailAlreadyExists;
  @Value("${validation.email.alreadyexists}")
  private String emailAlreadyExits;
  @Value("${validation.nic.alreadyexists}")
  private String nicAlreadyExists;
  @Value("${validation.mobile.alreadyexists}")
  private String mobileAlreadyExists;
  @Value("${validation.emergencycontact.alreadyexists}")
  private String emergencyContactAlreadyExists;
  @Value("${validation.emergencycontact.not.found}")
  private String emergencyContactNotFound;
  @Value("${validation.emergency.contact.already.in.contact.details}")
  private String emergencyContactAlreadyExistsInContactDetails;
  @Value("${validation.passport.number.already.exists}")
  private String passportNumberAlreadyExists;
  @Value("${validation.driving.license.already.exists}")
  private String drivingLicenseNumberAlreadyExists;
  @Value("${validation.employee.doesnotexist}")
  private String employeeNotExists;
  @Value("${email.already.verified}")
  private String emailAlreadyVerified;

  // Defect Tracker System
  @Value("${email.verified.failed}")
  private String emailVerifiedFailed;
  @Value("${defect.tracker.system.user}")
  private String notApplicableForDefectTrackerSystem;

  // Image upload
  @Value("${image.unsupported.message}")
  private String fileNotSupported;
  @Value("${file.not.found}")
  private String fileNotFound;

  // vaccination details
  @Value("${vaccination.details.not.found.in.employee}")
  private String vaccinationDetailsNotFound;
  @Value("${vaccination.details.not.found}")
  private String vaccineDetailsNotFound;

  // Referees Details
  @Value("${referees.not.found}")
  private String refereesNotFound;
  @Value("${referees.not.found.in.employee}")
  private String refereesNotFoundForEmployee;
  @Value("${referees.contact.no.already.exists}")
  private String refereesContactNoAlreadyExists;
  @Value("${referees.contact.same}")
  private String refereesContactSame;

  // Induction CheckList
  @Value("${induction.checklist.not.found}")
  private String inductionCheckListNotFound;
  @Value("${induction.checklist.not.found.in.employee}")
  private String noInductionCheckListInEmployee;

  // Exit Procedure
  @Value("${exit.procedure.not.found}")
  private String exitProcedureNotFound;
  @Value("${exit.procedure.not.found.in.employee}")
  private String noExitProcedureInEmployee;

  // Laptop Allocation
  @Value("${laptop.allocation.not.found}")
  private String laptopAllocationNotFound;
  @Value("${laptop.allocation.not.found.in.employee}")
  private String noLaptopAllocationInEmployee;
  @Value("${laptop.serial.number.already.exists}")
  private String laptopSerialNumberAlreadyExists;

  // Resignation
  @Value("${resignation.not.found}")
  private String resignationNotFound;
  @Value("${resignation.not.found.in.employee}")
  private String noResignationInEmployee;

  // Key Registry
  @Value("${key.registry.not.found}")
  private String keyRegistryNotFound;
  @Value("${key.registry.not.found.in.employee}")
  private String noKeyRegistryInEmployee;
  @Value("${key.number.already.exist}")
  private String keyNumberAlreadyExists;

  // Employee ID Card
  @Value("${employee.id.card.not.found}")
  private String employeeIdCardNotFound;
  @Value("${employee.id.card.not.found.in.employee}")
  private String noEmployeeIdCardInEmployee;

  // B-Card Details
  @Value("${b.card.not.found}")
  private String bCardNotFound;
  @Value("${b.card.not.found.in.employee}")
  private String noBCardInEmployee;
  @Value("${epf.no.already.exists}")
  private String epfNoAlreadyExists;

  // Professional Certificates
  @Value("${professional.certificates.not.found}")
  private String professionalCertificatesNotFound;
  @Value("${professional.certificates.not.found.in.employee}")
  private String noProfessionalCertificatesInEmployee;
  @Value("${professional.certificates.alreadyexists}")
  private String professionalCertificatesAlreadyExists;

  // Worked Project
  @Value("${worked.project.not.found}")
  private String workedProjectNotFound;
  @Value("${worked.project.not.found.in.work.experience.details}")
  private String workedProjectNotFoundInWorkExperienceDetails;
  @Value(("${worked.project.already.exists}"))
  private String workedProjectAlreadyExists;

  // Work Experience Details
  @Value("${work.experience.details.not.found}")
  private String workExperienceDetailsNotFound;
  @Value("${work.experience.details.not.found.in.employee}")
  private String workExperienceDetailsNotFoundInEmployee;
  @Value("${work.experience.details.already.exists}")
  private String workExperienceDetailsAlreadyExists;

  // Education Qualification
  @Value("${education.qualification.not.found}")
  private String educationQualificationNotFound;
  @Value("${education.qualification.not.found.in.employee}")
  private String educationQualificationNotFoundInEmployee;
  @Value("${education.qualification.already.exists}")
  private String educationQualificationAlreadyExists;
  @Value("${degeree.already.exists}")
  private String degreeAlreadyExists;

  // Emergency Contact Details
  @Value("${emergency.contact.no.exists}")
  private String emergencyContactNumberExists;

  // Contact Details
  @Value("${contact.no.exists.in.emergency.contact.no}")
  private String contactNoExistsInEmergencyContactDetails;
  @Value("${official.email.already.exists}")
  private String officialEmailAlreadyExists;

  // Promotion Details
  @Value("${promotion.details.already.exists}")
  private String promotionDetailsAlreadyExists;

  // Employment Category
  @Value("${employment.category.alreadyexists}")
  private String employmentCategoryAlreadyExists;
  @Value("${employment.category.notexists}")
  private String employmentCategoryNotexists;
  @Value("${employment.category.depend.in.employee.details}")
  private String employmentCategoryDepend;

  // Job title
  @Value("${jobtitle.already.exists}")
  private String jobTitleAlreadyExists;
  @Value("${jobtitle.id.not.found}")
  private String jobTitleIdNotFound;
  @Value("${jobtitle.depended.in.employee}")
  private String jobTitleDepended;

  // Change Password
  @Value("${validation.user.emailNotExit}")
  private String EmailNotExit;
  @Value("${validation.user.passwordNotMatch}")
  private String PasswordNotMatch;

  // Forget Password
  @Value("${validation.user.passwordTokenNotMatch}")
  private String PasswordTokenNotMatch;
  @Value("${referees.contact.exists.in.contact.details}")
  public String referenceContactExistsInContactDetails;
}
