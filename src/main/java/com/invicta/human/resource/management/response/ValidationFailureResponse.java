package com.invicta.human.resource.management.response;

import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.validation.ValidationFailure;

import java.util.Collections;
import java.util.List;

public class ValidationFailureResponse extends ApiResponse {

  private List<ValidationFailure> validationFailures;

  public ValidationFailureResponse(List<ValidationFailure> validationErrors) {
    super(RestApiResponseStatus.VALIDATION_FAILURE);
    this.validationFailures = validationErrors;
  }

  public ValidationFailureResponse(String message, String code) {
    super(RestApiResponseStatus.VALIDATION_FAILURE);
    ValidationFailure validationFailure = new ValidationFailure(message, code);
    this.validationFailures = Collections.singletonList(validationFailure);
  }

  public List<ValidationFailure> getValidationFailures() {
    return validationFailures;
  }

  public void setValidationFailures(List<ValidationFailure> validationFailures) {
    this.validationFailures = validationFailures;
  }
}
