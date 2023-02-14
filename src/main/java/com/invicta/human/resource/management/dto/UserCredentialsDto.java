package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialsDto {
  private long id;
  private String username;
  private String password;
  private String token;
  private boolean isUserActive;
  private String employeeId;
}
