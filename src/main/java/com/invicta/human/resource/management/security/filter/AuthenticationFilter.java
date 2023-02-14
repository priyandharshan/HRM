package com.invicta.human.resource.management.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invicta.human.resource.management.entities.UserCredentials;
import com.invicta.human.resource.management.repositories.RolePermissionRepository;
import com.invicta.human.resource.management.security.details.CustomUserDetails;
import com.invicta.human.resource.management.services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class AuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private RolePermissionRepository rolePermissionRepository;
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private UserCredentialsService userCredentialsService;

  @Value("${security.oauth2.client.client-id}")
  private String clientId;
  @Value("${security.oauth2.client.client-secret}")
  private String clientSecret;
  @Value("${security.oauth2.resource.token-info-uri}")
  private String checkTokenUri;

  /**
   * Same contract as for {@code doFilter}, but guaranteed to be just invoked once per request
   * within a single request thread. See {@link #shouldNotFilterAsyncDispatch()} for details.
   * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
   * default ServletRequest and ServletResponse ones.
   *
   * @param request
   * @param response
   * @param filterChain
   */
  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    try {
      if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
        throw new ServletException("OncePerRequestFilter just supports HTTP requests");
      }
      String jwt = getJwt(request);
      HttpHeaders headers = new HttpHeaders();
      headers.setBasicAuth(clientId, clientSecret);
      HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
      if (jwt != null) {
        ResponseEntity<Object> responseEntity =
            restTemplate.exchange(checkTokenUri + "?token=" + getJwt(request), HttpMethod.POST,
                httpEntity, Object.class);
        Map<String, Object> responses = (Map<String, Object>) responseEntity.getBody();
        HashSet<String> authorities = new HashSet<>();
        HashSet<String> tokenAuthorities =
            (HashSet<String>) objectMapper.convertValue(responses.get("authorities"), Set.class);
        if (tokenAuthorities != null)
          authorities.addAll(tokenAuthorities);
        if (responseEntity.getStatusCodeValue() == 200) {
          String username = responses.get("user_name").toString();
          CustomUserDetails userDetails =
              (CustomUserDetails) userDetailsService.loadUserByUsername(username);
          if (userCredentialsService.existsByUsername(username)) {
            UserCredentials userCredentials = userCredentialsService.findByUsername(username);
            if (rolePermissionRepository.existsByDesignationId(
                userCredentials.getEmployee().getDesignation().getId())) {
              List<GrantedAuthority> grandAuthority = new ArrayList<>();
              rolePermissionRepository.findAllByDesignationId(
                      userCredentials.getEmployee().getDesignation().getId())
                  .forEach((rolePermission) -> {
                    authorities.add(rolePermission.getPermission().getName());
                  });
              authorities.forEach((authority) -> {
                grandAuthority.add(new SimpleGrantedAuthority(authority));
              });
              UsernamePasswordAuthenticationToken token =
                  new UsernamePasswordAuthenticationToken(userDetails, null, grandAuthority);
              token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(token);
            }
          }
        }
      }
    } catch (Exception e) {
      logger.error("Could not set user authentication in security context", e);
    }
    filterChain.doFilter(request, response);
  }

  private String getJwt(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.replace("Bearer ", "");
    }
    return null;
  }
}
