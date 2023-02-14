package com.invicta.human.resource.management.response;

import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse extends ApiResponse {

  private String message;

  public BaseResponse(RestApiResponseStatus restApiResponseStatus, String message) {
   super(restApiResponseStatus);
    this.message = message;
  }
}
