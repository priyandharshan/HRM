package com.invicta.human.resource.management.entities;

import com.invicta.human.resource.management.enums.AppointmentType;
import com.invicta.human.resource.management.enums.NameTitles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employee")

@Getter
@Setter
public class Employee extends DateAudit {

  @Id
  private String id;
  @Enumerated(EnumType.STRING)
  private NameTitles title;
  @NotNull(message = "first name should not be null")
  @NotEmpty(message = "first name should not be empty")
  private String firstName;
  @NotNull(message = "last name should not be null")
  @NotEmpty(message = "last name should not be empty")
  private String lastName;

  @ManyToOne(optional = false)
  @JoinColumn(name = "designation_Id")
  private Designation designation;

  @ManyToOne
  @JoinColumn(name = "job_title_Id")
  private JobTitle jobTitle;

  @Enumerated(EnumType.STRING)
  private AppointmentType appointmentType;

  boolean isAssessmentApplicable;

  @Size(max = 12, min = 1, message = "Should be between 1 to 12") int assessmentPeriod;

  @Past
  private Date joinedDate;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private PersonalDetails personalDetails;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private ContactDetails contactDetails;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<EmergencyContactDetails> emergencyContactDetailsList;

  @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<ParDetails> parDetailsList;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<PromotionDetails> promotionDetailsList;

  @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private EmployeeProfileImage employeeProfileImage;
  private boolean isDefectTrackerSystemUser;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private InductionCheckList inductionCheckList;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private ExitProcedure exitProcedure;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private KeyRegistry keyRegistry;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Resignation resignation;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private LaptopAllocationDetails laptopAllocationDetails;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private EmployeeIDCard employeeIDCard;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<BCardDetails> bCardDetailsList;

  @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private UserCredentials userCredentials;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ProfessionalCertificates> professionalCertificatesList;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<VaccinationDetails> vaccinationDetailsList;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<RefereesDetails> refereesDetailsList;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<WorkExperienceDetails> workExperienceDetailsList;

  @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<EducationQualifications> educationQualificationsList;

  @ManyToOne
  @JoinColumn(name = "employmentCategory_Id")
  private EmploymentCategory employmentCategory;

  @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<LeaveAllocation> leaveAllocation;
}
