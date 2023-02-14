package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.entities.Designation;
import com.invicta.human.resource.management.entities.PromotionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImplementation implements EmailService {

  @Autowired
  JavaMailSender javaMailSender;
  @Autowired
  private DesignationService designationService;
  @Autowired
  private TemplateEngine templateEngine;


  @Value("${spring.email.verification}")
  String emailVerificationURL;
  @Value("${semita.url}")
  String semitaUrl;
  @Value("${spring.hrm.url}")
  String hrmUrl;

  /**
   * @param email
   * @param token
   * @author Priyadharshan
   * @date 26-10-2022
   */
  @Override
  @Async
  public void sendVerificationMail(String email, String token) {
    String subject = "Please verify your registration";
    String content =
        "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>" + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "<a>Your verification token is :  " + token + "<br>" + "Thank you,<br>" + "Invicta Innovations.";

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setTo(email);
      helper.setSubject(subject);
      content = content.replace("[[name]]", email);
      String verifyURL = emailVerificationURL;
      content = content.replace("[[URL]]", verifyURL);
      helper.setText(content, true);
      sendEmail(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send username and password to employee
   *
   * @param email    userCredential-object
   * @param password password-String
   * @author Priyadharshan
   */

  @Async
  @Override
  public void sendUsernamePassword(String email, String password) {
    String subject = "Welcome to Invicta Innovations";
    String content =
        "Username and password for Defect Tracking system and <br> " + "Human Resource Management System<br>" + "Username:  " + email + "<br>" + "Password:  " + password + "<br>" + "<h3><a href=\"[[URL]]\" target=\"_self\">Semita</a></h3><br>" + "<h3><a href=\"[[HRM]]\" target=\"_self\">Hrm System</a></h3><br>" + "Thank you,<br>" + "Invicta Innovations.";
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      mimeMessageHelper.setTo(email);
      mimeMessageHelper.setSubject(subject);
      String semitaUrlWeb = semitaUrl;
      content = content.replace("[[URL]]", semitaUrlWeb);
      content = content.replace("[[HRM]]", hrmUrl);
      mimeMessageHelper.setText(content, true);
      sendEmail(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  @Override
  @Async
  public void sendPromotionEmail(PromotionDetails promotionDetails) {
    String firstName = promotionDetails.getEmployee().getFirstName();
    String lastName = promotionDetails.getEmployee().getLastName();
    String toAddress = promotionDetails.getEmployee().getContactDetails().getEmail();
    String subject = "Congratulations! " + firstName + " " + lastName;
    Designation designation =
        designationService.getDesignationById(promotionDetails.getPromotedDesignation().getId());
    Context context = new Context();
    context.setVariable("designation", designation);

    String process = templateEngine.process("email/welcome", context);
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      mimeMessageHelper.setTo(toAddress);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(process, true);
      sendEmail(mimeMessage);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @Async
  public void changePasswordMailNotification(String username) {
    String subject = "Successfully Password Changed";
    String content =
        "Dear [[name]],<br>" + "Password changed successfully:<br>" + "<br>" + "Thank you,<br>";
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      content = content.replace("[[name]]", username);
      mimeMessageHelper.setTo(username);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(content, true);
      sendEmail(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }

  @Override
  @Async
  public void resetPasswordMailNotification(String email) {
    String subject = "Successfully Reset Password";
    String content =
        "Dear [[name]],<br>" + "Password reset successfully:<br>" + "<br>" + "Thank you,<br>";
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      mimeMessageHelper.setTo(email);
      mimeMessageHelper.setSubject(subject);
      content = content.replace("[[name]]", email);
      mimeMessageHelper.setText(content, true);
      sendEmail(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }

  @Override
  @Async
  public void sendPasswordToken(String email, String passwordToken) {
    String subject = "Reset Password Token";
    String content =
        "Dear [[name]],<br>" + "Your Password Token : " + passwordToken + "<br>" + "Thank you,<br>";
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      mimeMessageHelper.setTo(email);
      mimeMessageHelper.setSubject(subject);
      content = content.replace("[[name]]", email);
      mimeMessageHelper.setText(content, true);
      sendEmail(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }

  @Override
  @Async
  public void sendDeactivateMail(String email) {
    String subject = "User Deactivate";
    String content = "Dear [[name]] Your account deactivate " + "<br>" + "Thank you,<br>";
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    try {
      mimeMessageHelper.setTo(email);
      mimeMessageHelper.setSubject(subject);
      content = content.replace("[[name]]", email);
      mimeMessageHelper.setText(content, true);
      sendEmail(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendEmail(MimeMessage mimeMessage) {
    javaMailSender.send(mimeMessage);
  }
}
