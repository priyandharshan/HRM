package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ContactDetailsDto;
import com.invicta.human.resource.management.dto.UserCredentialsDto;
import com.invicta.human.resource.management.entities.ContactDetails;
import com.invicta.human.resource.management.entities.UserCredentials;

public interface UserCredentialsService {

  void saveUserCredentials(ContactDetails contactDetails);

  void updateUserCredentials(ContactDetailsDto contactdetailsDto);

  void deleteUserCredentials(String id);

  boolean isActive(UserCredentialsDto userCredentialsDto);

  UserCredentials getUserCredentialsByUsername(String username);

  boolean existsByEmployeeId(String employeeId);

  UserCredentials findByUsername(String username);

  UserCredentials findByEmployeeId(String employeeId);

  boolean existsByUsername(String username);

  String getPassword(String name);

  void changePassword(String newPassword, String username);

  boolean isUsernameExists(String username);

  void saveResetPasswordToken(String email);

  void resetPassword(String newPassword, String email);

  boolean isTokenMatch(String passwordToken, String userName);

  void deactivateUserCredentials(String id);
}
