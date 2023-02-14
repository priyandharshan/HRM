package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteDetailsDto {
  private EmployeeDto employeeDto;
  private PersonalDetailsDto personalDetailsDto;
  private ContactDetailsDto contactDetailsDto;
  private EmergencyContactDetailsDto emergencyContactDetailsDto;
  private ParDetailsDto parDetailsDto;
  private PromotionDetailsDto promotionDetailsDto;
  private InductionCheckListDto inductionCheckListDto;
  private EmployeeProfileImageDto employeeProfileImageDto;
}
