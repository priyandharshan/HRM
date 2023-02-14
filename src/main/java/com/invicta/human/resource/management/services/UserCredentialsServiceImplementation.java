package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ContactDetailsDto;
import com.invicta.human.resource.management.dto.UserCredentialsDto;
import com.invicta.human.resource.management.entities.ContactDetails;
import com.invicta.human.resource.management.entities.UserCredentials;
import com.invicta.human.resource.management.repositories.UserCredentialsRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserCredentialsServiceImplementation implements UserCredentialsService {
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  UserCredentialsRepository userCredentialsRepository;
  @Autowired
  private EmailService emailService;
  @Autowired
  private ContactDetailsService contactDetailsService;

  /**
   * Add user details of an employee
   *
   * @param contactDetails userCredentials Object
   * @author Priyadharshan
   * @date 21-10-2022
   */
  @Override
  public void saveUserCredentials(ContactDetails contactDetails) {
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setEmployee(contactDetails.getEmployee());
    String randomCode = RandomString.make(10);
    userCredentials.setUsername(contactDetails.getEmail());
    userCredentials.setToken(randomCode);
    userCredentials.setUserActive(true);
    userCredentialsRepository.save(userCredentials);
    emailService.sendVerificationMail(contactDetails.getEmail(), randomCode);
  }

  /**
   * Update userCredentials
   *
   * @param contactDetailsDto userCredential-Object
   * @author Priyadharshan
   */
  @Override
  public void updateUserCredentials(ContactDetailsDto contactDetailsDto) {
    UserCredentials userCredentials =
        userCredentialsRepository.findByEmployeeId(contactDetailsDto.getEmployeeId());
    userCredentials.setUsername(contactDetailsDto.getEmail());
    String randomCode = RandomString.make(10);
    userCredentials.setActive(false);
    userCredentials.setPassword(null);
    userCredentials.setToken(randomCode);
    userCredentialsRepository.save(userCredentials);
    emailService.sendVerificationMail(contactDetailsDto.getEmail(), randomCode);
  }

  /**
   * @param id - Employee id
   */
  @Transactional
  public void deleteUserCredentials(String id) {
    userCredentialsRepository.deleteByEmployeeId(id);
  }

  /**
   * Check Employee is active or not
   *
   * @param userCredentialsDto username
   * @return
   * @throws NullPointerException
   */

  @Override
  public boolean isActive(UserCredentialsDto userCredentialsDto) throws NullPointerException {
    ContactDetails contactDetails =
        contactDetailsService.getContactDetailsByEmail(userCredentialsDto.getUsername());
    return userCredentialsRepository.findByEmployeeId(contactDetails.getEmployee().getId())
        .isActive();
  }

  @Override
  public UserCredentials getUserCredentialsByUsername(String username) {
    return userCredentialsRepository.findByUsername(username);
  }

  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return userCredentialsRepository.existsByEmployeeId(employeeId);
  }

  @Override
  public UserCredentials findByUsername(String username) {
    return userCredentialsRepository.findByUsername(username);
  }

  @Override
  public UserCredentials findByEmployeeId(String employeeId) {
    return userCredentialsRepository.findByEmployeeId(employeeId);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userCredentialsRepository.existsByUsername(username);
  }

  @Override
  public String getPassword(String username) {
    return userCredentialsRepository.findByUsername(username).getPassword();
  }

  @Override
  public void changePassword(String newPassword, String username) {
    UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
    userCredentials.setPassword(newPassword);
    userCredentialsRepository.save(userCredentials);
  }

  @Override
  public boolean isUsernameExists(String username) {
    return userCredentialsRepository.existsByUsername(username);
  }

  @Override
  public void saveResetPasswordToken(String email) {
    UserCredentials userCredentials = userCredentialsRepository.findByUsername(email);
    String randomCode = RandomString.make(10);
    userCredentials.setToken(randomCode);
    userCredentialsRepository.save(userCredentials);
    emailService.sendPasswordToken(email, randomCode);
  }

  @Override
  public void resetPassword(String newPassword, String email) {
    UserCredentials userCredentials = userCredentialsRepository.findByUsername(email);
    userCredentials.setPassword(passwordEncoder.encode(newPassword));
    userCredentials.setToken(null);
    userCredentialsRepository.save(userCredentials);
  }

  @Override
  public boolean isTokenMatch(String passwordToken, String userName) {
    return userCredentialsRepository.findByUsername(userName).getToken().equals(passwordToken);
  }

  @Override
  public void deactivateUserCredentials(String id) {
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUserActive(false);
    userCredentialsRepository.save(userCredentials);
    emailService.sendDeactivateMail(userCredentials.getUsername());
  }
}
