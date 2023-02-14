package com.invicta.human.resource.management.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.utils.Constants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthEntryPoint implements AuthenticationEntryPoint {
  /**
   * Commences an authentication scheme.
   * <p>
   * <code>ExceptionTranslationFilter</code> will populate the <code>HttpSession</code>
   * attribute named
   * <code>AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY</code>
   * with the requested target URL before calling this method.
   * <p>
   * Implementations should modify the headers on the <code>ServletResponse</code> as necessary to
   * commence the authentication process.
   *
   * @param request       that resulted in an <code>AuthenticationException</code>
   * @param response      so that the user agent can begin authentication
   * @param authException that caused the invocation
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    response.setContentType("application/json");
    response.getWriter().write(mapper.writeValueAsString(
        new BasicResponse(RestApiResponseStatus.ERROR, Constants.TOKEN_NOT_PRESENT)));
  }
}
