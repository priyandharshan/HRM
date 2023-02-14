package com.invicta.human.resource.management.response;

import java.util.HashMap;
import java.util.Map;

import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse<T> extends BaseResponse {
  private Map<String, T> result = new HashMap<>();

  public ContentResponse(String key, T dto, RestApiResponseStatus restApiResponseStatus, String message) {
    super(restApiResponseStatus, message);
    result.put(key, dto);
  }
  public ContentResponse() {
    super(RestApiResponseStatus.OK,"");
  }

  private Map<String, T> results = new HashMap<String, T>();

  public ContentResponse(String key, T dto, RestApiResponseStatus restApiResponseStatus) {
    super(restApiResponseStatus,"");
    results.put(key, dto);
  }

  public Map<String, T> getResults() {
    return results;
  }

  public void setResults(Map<String, T> results) {
    this.results = results;
  }
}
