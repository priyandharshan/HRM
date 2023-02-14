package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ForgotEmailDto {
  @NotNull(message = "{user.email.null}")
  @NotEmpty(message = "{user.email.empty}")
  @NotBlank(message = "{user.email.blank}")
  private String email;
}
