package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.DefectTrackerDto;
import com.invicta.human.resource.management.dto.DefectTrackerEmployeeDto;
import com.invicta.human.resource.management.dto.UserCredentialsDto;
import com.invicta.human.resource.management.entities.ContactDetails;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.UserCredentials;
import com.invicta.human.resource.management.repositories.UserCredentialsRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DefectTrackerServiceImplementation implements DefectTrackerService {

  @Autowired
  UserCredentialsRepository userCredentialsRepository;
  @Autowired
  private ContactDetailsService contactDetailsService;
  @Autowired
  RestTemplate restTemplate;
  @Value("${spring.restcallurl}")
  String restApiURL;
  @Value("${rest.call.url.list}")
  String restCallUrlList;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  EmailService emailService;
  @Autowired
  private EmployeeService employeeService;

  /**
   * Verify the email
   *
   * @param userCredentialsDto to verify email
   * @return employee id
   * @author Priyadharshan
   */

  @Transactional
  public String Verify(UserCredentialsDto userCredentialsDto) {
    ContactDetails contactDetails =
        contactDetailsService.getContactDetailsByEmail(userCredentialsDto.getUsername());
    if (userCredentialsDto.getToken()
        .equals(contactDetails.getEmployee().getUserCredentials().getToken())) {
      String password = RandomString.make(10);
      UserCredentials userCredentials =
          userCredentialsRepository.findByEmployeeId(contactDetails.getEmployee().getId());
      userCredentials.setActive(true);
      userCredentials.setPassword(bCryptPasswordEncoder.encode(password));
      emailService.sendUsernamePassword(userCredentialsDto.getUsername(), password);
      userCredentialsRepository.save(userCredentials);
      return userCredentials.getEmployee().getId();
    }
    return null;
  }

  /**
   * Send required data to the Defect Tracker System
   *
   * @param id
   * @author Priyadharshan
   */
  @Transactional
  public void defectTrackerDto(String id) {
    DefectTrackerDto defectTrackerDto = new DefectTrackerDto();
    Employee employee = employeeService.getEmployeeDetails(id);
    BeanUtils.copyProperties(employee, defectTrackerDto);
    defectTrackerDto.setEmployeeId(id);
    defectTrackerDto.setDesignationName(employee.getDesignation().getDesignation());
    defectTrackerDto.setMail(employee.getContactDetails().getEmail());
    defectTrackerDto.setContactNo(employee.getContactDetails().getMobileNumber());
    defectTrackerDto.setGender(employee.getPersonalDetails().getGender());
    defectTrackerDto.setPassword(employee.getUserCredentials().getPassword());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> httpEntity = new HttpEntity<>(defectTrackerDto, httpHeaders);
    restTemplate.postForObject(restApiURL, httpEntity, String.class);
  }

  /**
   * send list of defectTracker users Modified By @author Priyadharshan on 23-11-2022
   *
   * @param employeeIdList get employeeId as list
   */
  @Transactional
  public void sendAsListToDefectTrackerSystem(List<DefectTrackerEmployeeDto> employeeIdList) {
    List<DefectTrackerDto> defectTrackerDtoList = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();
    for (DefectTrackerEmployeeDto defectTrackerEmployeeDto : employeeIdList) {
      DefectTrackerDto defectTrackerDto = new DefectTrackerDto();
      Employee employee =
          employeeService.getEmployeeDetails(defectTrackerEmployeeDto.getEmployeeId());
      if (employee.getUserCredentials().isActive()) {
        BeanUtils.copyProperties(employee, defectTrackerDto);
        defectTrackerDto.setEmployeeId(employee.getId());
        defectTrackerDto.setDesignationName(employee.getDesignation().getDesignation());
        defectTrackerDto.setMail(employee.getContactDetails().getEmail());
        defectTrackerDto.setContactNo(employee.getContactDetails().getMobileNumber());
        defectTrackerDto.setGender(employee.getPersonalDetails().getGender());
        defectTrackerDto.setPassword(employee.getUserCredentials().getPassword());
        defectTrackerDtoList.add(defectTrackerDto);
        employee.setDefectTrackerSystemUser(true);
        employeeList.add(employee);
      }
    }
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<Object> httpEntity = new HttpEntity<>(defectTrackerDtoList, httpHeaders);
    restTemplate.postForObject(restCallUrlList, httpEntity, String.class);
    employeeService.updateListEmployee(employeeList);
  }

}
