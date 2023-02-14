package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ForgotPasswordDto {
  @NotNull(message = "{user.token.null}")
  @NotEmpty(message = "{user.token.empty}")
  @NotBlank(message = "{user.token.blank}")
  private String passwordToken;
  @NotNull(message = "{user.password.null}")
  @NotEmpty(message = "{user.password.empty}")
  @NotBlank(message = "{user.password.blank}")
  private String password;
  @NotNull(message = "{user.email.null}")
  @NotEmpty(message = "{user.email.empty}")
  @NotBlank(message = "{user.email.blank}")
  private String email;
}
