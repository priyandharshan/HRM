package com.invicta.human.resource.management.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationFailure {

  private String message;
  private String code;

  public ValidationFailure(String message, String code) {
    this.message = message;
    this.code = code;
  }

}
