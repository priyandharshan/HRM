package com.invicta.human.resource.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class JobTitleDto {
  private Long id;
  @NotEmpty(message = "Job title can not be empty")
  @NotNull(message = "Job title can not be null")
  private String jobTitle;
}
