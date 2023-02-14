package com.invicta.human.resource.management.response;

import com.invicta.human.resource.management.enums.RestApiResponseStatus;

public class BasicResponse extends ApiResponse {

  private String message;

  public BasicResponse(RestApiResponseStatus restApiResponseStatus) {
    super(restApiResponseStatus);
  }

  public BasicResponse() {
    super(RestApiResponseStatus.OK);
  }

  public BasicResponse(RestApiResponseStatus restApiResponseStatus, String message) {
    super(restApiResponseStatus);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String Message) {
    this.message = message;
  }
}
