package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.entities.PromotionDetails;

import javax.mail.internet.MimeMessage;

public interface EmailService {

  void sendVerificationMail(String email, String token);

  void sendUsernamePassword(String email, String password);

  void sendPromotionEmail(PromotionDetails promotionDetails);

  void changePasswordMailNotification(String name);

  void resetPasswordMailNotification(String name);

  void sendPasswordToken(String email, String passwordToken);

  void sendDeactivateMail(String username);

  void sendEmail(MimeMessage mimeMessage);
}
