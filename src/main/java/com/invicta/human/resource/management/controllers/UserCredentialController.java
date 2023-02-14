package com.invicta.human.resource.management.controllers;

import com.invicta.human.resource.management.dto.ChangePasswordDto;
import com.invicta.human.resource.management.dto.ForgotEmailDto;
import com.invicta.human.resource.management.dto.ForgotPasswordDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.response.ValidationFailureResponse;
import com.invicta.human.resource.management.services.EmailService;
import com.invicta.human.resource.management.services.UserCredentialsService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.ValidationFailureResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserCredentialController {
  @Autowired
  private UserCredentialsService userCredentialsService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmailService emailService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  private static final Logger log = LoggerFactory.getLogger(UserCredentialController.class);

  @PutMapping(value = EndPointURI.CHANGE_PASSWORD)
  public ResponseEntity<Object> changePassword(
      @Valid @RequestBody ChangePasswordDto changePasswordDto) {
    if (!userCredentialsService.isUsernameExists(changePasswordDto.getUsername())) {
      log.info("Exists Username");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAIL,
          validationFailureResponseCode.getEmailNotExit()), HttpStatus.BAD_REQUEST);
    }
    String password = userCredentialsService.getPassword(changePasswordDto.getUsername());
    if (passwordEncoder.matches(changePasswordDto.getOldpassword(), password)) {
      String code = passwordEncoder.encode(changePasswordDto.getNewpassword());
      userCredentialsService.changePassword(code, changePasswordDto.getUsername());
      emailService.changePasswordMailNotification(changePasswordDto.getUsername());
      log.info("Password Changed Successfully");
      return new ResponseEntity<>(
          new BasicResponse(RestApiResponseStatus.OK, Constants.PASSWORD_CHANGED_SUCCESS),
          HttpStatus.OK);
    }
    log.info("Oled Password Not Match");
    return new ResponseEntity<Object>(new ValidationFailureResponse(Constants.PASSWORD,
        validationFailureResponseCode.getPasswordNotMatch()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndPointURI.FORGOT_PASSWORD)
  public ResponseEntity<Object> forgetPassword(@Valid @RequestBody ForgotEmailDto forgotEmailDto) {
    if (!userCredentialsService.isUsernameExists(forgotEmailDto.getEmail())) {
      log.info("Exists User Name");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAIL,
          validationFailureResponseCode.getEmailNotExit()), HttpStatus.BAD_REQUEST);
    }
    userCredentialsService.saveResetPasswordToken(forgotEmailDto.getEmail());
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.TOKEN_SEND_SUCCESS), HttpStatus.OK);
  }

  @PutMapping(value = EndPointURI.RESET_PASSWORD)
  public ResponseEntity<Object> resetPassword(
      @Valid @RequestBody ForgotPasswordDto forgotPasswordDto) {
    if (!userCredentialsService.isUsernameExists(forgotPasswordDto.getEmail())) {
      log.info("Exists User Name");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAIL,
          validationFailureResponseCode.getEmailNotExit()), HttpStatus.BAD_REQUEST);
    }
    if (!userCredentialsService.isTokenMatch(forgotPasswordDto.getPasswordToken(),
        forgotPasswordDto.getEmail())) {
      log.info("Exists User Name");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.RESET_PASSWORD_TOKEN,
          validationFailureResponseCode.getPasswordTokenNotMatch()), HttpStatus.BAD_REQUEST);
    }
    userCredentialsService.resetPassword(forgotPasswordDto.getPassword(),
        forgotPasswordDto.getEmail());
    emailService.resetPasswordMailNotification(forgotPasswordDto.getEmail());
    log.info("Password Reset Successfully");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.RESET_PASSWORD_SUCCESS),
        HttpStatus.OK);
  }
}
