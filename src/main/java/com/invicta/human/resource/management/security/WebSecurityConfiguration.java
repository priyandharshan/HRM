package com.invicta.human.resource.management.security;

import com.invicta.human.resource.management.security.filter.AuthenticationFilter;
import com.invicta.human.resource.management.utils.EndPointURI;
import com.invicta.human.resource.management.utils.PrivilegeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableResourceServer
public class WebSecurityConfiguration extends ResourceServerConfigurerAdapter {

  @Autowired
  private RestAuthEntryPoint restAuthEntryPoint;
  @Autowired
  private AuthenticationFilter authenticationFilter;

  private static final String RESOURCE_ID = "resource-server-rest-api";
  private static final String[] AUTH_WHITELIST =
      {"/v2/api-docs", "/swagger-resources/**", "swagger-ui.html", "/webjars/**", "/oauth/token",
          "/email/verify", EndPointURI.PROFILE_IMAGE + "**"};

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID);
  }

  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(EndPointURI.FORGOT_PASSWORD).permitAll()
        .antMatchers(EndPointURI.RESET_PASSWORD).permitAll()
        .antMatchers(EndPointURI.CHANGE_PASSWORD).permitAll()

        // Designation
        .antMatchers(HttpMethod.POST, EndPointURI.DESIGNATION)
        .hasAuthority(PrivilegeConstants.ADD_JOB_CATEGORY)
        .antMatchers(HttpMethod.GET, EndPointURI.DESIGNATION)
        .hasAnyAuthority(PrivilegeConstants.ADD_JOB_CATEGORY,
            PrivilegeConstants.DELETE_JOB_CATEGORY, PrivilegeConstants.GET_ALL_DESIGNATION,
            PrivilegeConstants.GET_JOB_CATEGORY_BY_ID, PrivilegeConstants.UPDATE_JOB_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndPointURI.DESIGNATION)
        .hasAuthority(PrivilegeConstants.UPDATE_JOB_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndPointURI.JOB_TITLE_BY_ID)
        .hasAuthority(PrivilegeConstants.DELETE_JOB_CATEGORY)
        .antMatchers(HttpMethod.GET, EndPointURI.DESIGNATION_SEARCH_PAGINATION)
        .hasAuthority(PrivilegeConstants.GET_LICENCE_SEARCH)

        // Personal details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_PERSONAL_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_PERSONAL_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.UPDATE_PERSONAL_DETAILS)
        .hasAuthority(PrivilegeConstants.UPDATE_PERSONAL_DETAILS)

        // Employee image
        .antMatchers(HttpMethod.POST, EndPointURI.IMAGE)
        .hasAuthority(PrivilegeConstants.ADD_PROFILE_IMAGE).antMatchers(HttpMethod.PUT)
        .hasAuthority(PrivilegeConstants.UPDATE_PERSONAL_DETAILS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.IMAGE)
        .hasAuthority(PrivilegeConstants.DELETE_PROFILE_IMAGE)

        // Contact details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_CONTACT_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_CONTACT_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.UPDATE_CONTACT_DETAILS)
        .hasAuthority(PrivilegeConstants.UPDATE_CONTACT_DETAILS)

        // Emergency contact details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_EMERGENCY_CONTACT_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_EMERGENCY_CONTACT_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.UPDATE_EMERGENCY_CONTACT_DETAILS)
        .hasAuthority(PrivilegeConstants.UPDATE_EMERGENCY_CONTACT_DETAILS)

        // Promotion details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_PROMOTION_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_PROMOTION_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.PROMOTION_DETAILS)
        .hasAuthority(PrivilegeConstants.UPDATE_PROMOTION_DETAILS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.PROMOTION_DETAILS)
        .hasAuthority(PrivilegeConstants.DELETE_PROMOTION_DETAILS)

        // PAR Details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_PAR_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_PAR_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.PAR_DETAILS_LIST)
        .hasAuthority(PrivilegeConstants.UPDATE_PAR_DETAILS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.PAR_DETAILS)
        .hasAuthority(PrivilegeConstants.DELETE_PAR_DETAILS)

        // Employee
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_EMPLOYEE)
        .hasAuthority(PrivilegeConstants.ADD_EMPLOYEE)
        .antMatchers(HttpMethod.GET, EndPointURI.EMPLOYEES)
        .hasAnyAuthority(PrivilegeConstants.GET_ALL_EMPLOYEES, PrivilegeConstants.DELETE_EMPLOYEE,
            PrivilegeConstants.UPDATE_EMPLOYEES, PrivilegeConstants.ADD_EMPLOYEE)
        .antMatchers(HttpMethod.PUT, EndPointURI.EMPLOYEES)
        .hasAuthority(PrivilegeConstants.UPDATE_EMPLOYEES)
        .antMatchers(HttpMethod.DELETE, EndPointURI.EMPLOYEE)
        .hasAuthority(PrivilegeConstants.DELETE_EMPLOYEE)

        // Job Category
        .antMatchers(HttpMethod.POST, EndPointURI.DESIGNATION)
        .hasAuthority(PrivilegeConstants.ADD_JOB_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndPointURI.DESIGNATIONBYID)
        .hasAuthority(PrivilegeConstants.DELETE_JOB_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndPointURI.DESIGNATION)
        .hasAuthority(PrivilegeConstants.UPDATE_JOB_CATEGORY)
        .antMatchers(HttpMethod.GET, EndPointURI.DESIGNATION)
        .hasAuthority(PrivilegeConstants.GET_ALL_DESIGNATION)

        // Job Title
        .antMatchers(HttpMethod.POST, EndPointURI.JOB_TITLE)
        .hasAuthority(PrivilegeConstants.ADD_JOB_TITLE)
        .antMatchers(HttpMethod.PUT, EndPointURI.JOB_TITLE)
        .hasAuthority(PrivilegeConstants.UPDATE_JOB_TITLE)
        .antMatchers(HttpMethod.GET, EndPointURI.JOB_TITLE)
        .hasAuthority(PrivilegeConstants.GET_JOB_TITLE)

        // Employment Category
        .antMatchers(HttpMethod.POST, EndPointURI.EMPLOYMENTCATEGORY)
        .hasAuthority(PrivilegeConstants.ADD_EMPLOYMENT_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndPointURI.EMPLOYMENTCATEGORYBYID)
        .hasAuthority(PrivilegeConstants.DELETE_EMPLOYMENT_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndPointURI.EMPLOYMENTCATEGORY)
        .hasAuthority(PrivilegeConstants.UPDATE_EMPLOYMENT_CATEGORY)
        .antMatchers(HttpMethod.GET, EndPointURI.EMPLOYMENTCATEGORY)
        .hasAuthority(PrivilegeConstants.GET_ALL_EMPLOYMENT_CATEGORY)

        // B-Card'
        .antMatchers(HttpMethod.POST, EndPointURI.B_CARD)
        .hasAuthority(PrivilegeConstants.ADD_BCARD_DETAILS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.B_CARD)
        .hasAuthority(PrivilegeConstants.DELETE_BCARD_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.B_CARD_BY_ID)
        .hasAuthority(PrivilegeConstants.UPDATE_BCARD_DETAILS)

        // Eductaion Qulaification
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_EDUCATION_QUALIFICATION)
        .hasAuthority(PrivilegeConstants.ADD_EDUCATION_QUALIFICATION)
        .antMatchers(HttpMethod.DELETE, EndPointURI.EDUCATION_QUALIFICATION_BY_ID)
        .hasAuthority(PrivilegeConstants.DELETE_EDUCATION_QUALIFICATION)
        .antMatchers(HttpMethod.PUT, EndPointURI.ADD_EDUCATION_QUALIFICATION)
        .hasAuthority(PrivilegeConstants.UPDATE_EDUCATION_QUALIFICATION)
        .antMatchers(HttpMethod.GET, EndPointURI.GET_ALL_INSTITUTES)
        .hasAuthority(PrivilegeConstants.GET_ALL_INSTITUION)

        // Employee ID Card
        .antMatchers(HttpMethod.POST, EndPointURI.EMPLOYEE_ID_CARD)
        .hasAuthority(PrivilegeConstants.ADD_EMPLOYEE_ID_CARD)
        .antMatchers(HttpMethod.PUT, EndPointURI.EMPLOYEE_ID_CARD)
        .hasAuthority(PrivilegeConstants.UPDATE_EMPLOYEE_ID_CARD)

        // Exit Procedure
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_EXIT_PROCEDURE)
        .hasAuthority(PrivilegeConstants.ADD_EXIT_PROCEDURE)
        .antMatchers(HttpMethod.PUT, EndPointURI.ADD_EXIT_PROCEDURE)
        .hasAuthority(PrivilegeConstants.UPDATE_EXIT_PROCEDURE)

        // Induction Check List
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_INDUCTIONCHECKLIST)
        .hasAuthority(PrivilegeConstants.ADD_INDUCTION_CHECK_LIST)
        .antMatchers(HttpMethod.PUT, EndPointURI.ADD_INDUCTIONCHECKLIST)
        .hasAuthority(PrivilegeConstants.UPDATE_INDUCTION_CHECK_LIST)

        // Key Registry
        .antMatchers(HttpMethod.POST, EndPointURI.KEY_REGISTRY)
        .hasAuthority(PrivilegeConstants.ADD_KEY_REGISTRY)
        .antMatchers(HttpMethod.PUT, EndPointURI.KEY_REGISTRY)
        .hasAuthority(PrivilegeConstants.UPDATE_KEY_REGISTRY)

        // Laptop Allocation
        .antMatchers(HttpMethod.POST, EndPointURI.LAPTOP_ALLOCATION)
        .hasAuthority(PrivilegeConstants.ADD_LAPTOP_ALLOCATION)
        .antMatchers(HttpMethod.PUT, EndPointURI.LAPTOP_ALLOCATION)
        .hasAuthority(PrivilegeConstants.UPDATE_LAPTOP_ALLOCATION)

        // Professional Certificate
        .antMatchers(HttpMethod.POST, EndPointURI.PROFESSIONAL_CERTIFICATES)
        .hasAuthority(PrivilegeConstants.ADD_PROFESSIONAL_CERTIFICATES)
        .antMatchers(HttpMethod.DELETE, EndPointURI.PROFESSIONAL_CERTIFICATES_BY_ID)
        .hasAuthority(PrivilegeConstants.DELETE_PROFESSIONAL_CERTIFICATES)
        .antMatchers(HttpMethod.PUT, EndPointURI.PROFESSIONAL_CERTIFICATES)
        .hasAuthority(PrivilegeConstants.UPDATE_PROFESSIONAL_CERTIFICATES)

        // Resignation
        .antMatchers(HttpMethod.POST, EndPointURI.RESIGNATION)
        .hasAuthority(PrivilegeConstants.ADD_RESIGNATION)
        .antMatchers(HttpMethod.PUT, EndPointURI.RESIGNATION)
        .hasAuthority(PrivilegeConstants.UPDATE_RESIGNATION)

        // Vaccination Details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_VACCINATION_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_VACCINATION_DETAILS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.VACCINATION_DETAILS_BY_ID)
        .hasAuthority(PrivilegeConstants.DELETE_VACCINATION_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.ADD_VACCINATION_DETAILS)
        .hasAuthority(PrivilegeConstants.UPDATE_VACCINATION_DETAILS)

        // Worked Project
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_WORKED_PROJECTS)
        .hasAuthority(PrivilegeConstants.ADD_WORKED_PROJECTS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.WORKED_PROJECTS_BY_ID)
        .hasAuthority(PrivilegeConstants.DELETE_WORKED_PROJECTS)
        .antMatchers(HttpMethod.PUT, EndPointURI.ADD_WORKED_PROJECTS)
        .hasAuthority(PrivilegeConstants.UPDATE_WORKED_PROJECTS)

        // Work Experience Details
        .antMatchers(HttpMethod.POST, EndPointURI.ADD_WORK_EXPERIENCE_DETAILS)
        .hasAuthority(PrivilegeConstants.ADD_WORK_EXPERIENCE_DETAILS)
        .antMatchers(HttpMethod.DELETE, EndPointURI.WORK_EXPERIENCE_DETAILS_BY_ID)
        .hasAuthority(PrivilegeConstants.DELETE_WORK_EXPERIENCE_DETAILS)
        .antMatchers(HttpMethod.PUT, EndPointURI.ADD_WORK_EXPERIENCE_DETAILS)
        .hasAuthority(PrivilegeConstants.UPDATE_WORK_EXPERIENCE_DETAILS)

        // Role Permission
        .antMatchers(HttpMethod.POST, EndPointURI.ROLE_PERMISSION)
        .hasAuthority(PrivilegeConstants.ADD_ROLE_PERMISSION_DETAILS)
        .antMatchers(HttpMethod.GET, EndPointURI.ROLE_PERMISSION_BY_ROLE)
        .hasAuthority(PrivilegeConstants.GET_ROLE_PERMISSION_DETAILS_BY_ID)

        .anyRequest().authenticated().and().headers().frameOptions();

    httpSecurity.exceptionHandling().authenticationEntryPoint(restAuthEntryPoint);
    httpSecurity.addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
