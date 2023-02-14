package com.invicta.human.resource.management.enums;

public enum RestApiResponseStatus {
  OK("SUCCESS", 200), CREATED("CREATED", 210), VALIDATION_FAILURE("VALIDATION_FAILURE",
      400), FORBIDDEN("FORBIDDEN", 410), ERROR("FAILURE", 500), NOT_FOUND("NOT_FOUND", 404);

  private String status;

  private Integer code;

  RestApiResponseStatus(String status, Integer code) {
    this.status = status;
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public Integer getCode() {
    return code;
  }
}
