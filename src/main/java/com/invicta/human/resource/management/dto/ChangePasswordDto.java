package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordDto {
  @NotNull(message = "{user.oldpassword.null}")
  @NotEmpty(message = "{user.oldpassword.empty}")
  @NotBlank(message = "{user.oldpassword.blank}")
  private String oldpassword;
  @NotNull(message = "{user.newpassword.null}")
  @NotEmpty(message = "{user.newpassword.empty}")
  @NotBlank(message = "{user.newpassword.blank}")
  private String newpassword;
  @NotNull(message = "{user.username.null}")
  @NotEmpty(message = "{user.username.empty}")
  @NotBlank(message = "{user.username.blank")
  private String username;
}
