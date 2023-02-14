package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileDetailsDto {

  private ResponseEmployeeDto responseEmployeeDto;
  private PersonalDetailsDto personalDetailsDto;
  private ContactDetailsDto contactDetailsDto;
  private List<EmergencyContactDetailsDto> emergencyContactDetailsDtoList;
  private List<ParDetailsDto> parDetailsDtoList;
  private List<ResponsePromotionDetailsDto> responsePromotionDetailsDtoList;
  private EmployeeProfileImageDto employeeProfileImageDto;
  private InductionCheckListDto inductionCheckListDto;
  private List<RefereesDetailsDto> refereesDetailsDtoList;
  private List<VaccinationDetailsDto> vaccinationDetailsDtoList;
  private List<BCardDetailsDto> bCardDetailsDtoList;
  private LaptopAllocationDetailsDto laptopAllocationDetailsDto;
  private EmployeeIDCardDto employeeIDCardDto;
  private ResignationDto resignationDto;
  private ExitProcedureDto exitProcedureDto;
  private KeyRegistryDto keyRegistryDto;
  private List<ProfessionalCertificatesDto> professionalCertificatesDtoList;
  private List<ResponseWorkExperienceDetailsDto> responseWorkExperienceDetailsDtoList;
  private List<EducationQualificationDto> educationQualificationDtoList;
}
