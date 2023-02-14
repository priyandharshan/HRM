package com.invicta.human.resource.management.controllers;

import java.security.Principal;
import java.util.List;

import com.invicta.human.resource.management.response.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.invicta.human.resource.management.dto.RolePermissionDto;
import com.invicta.human.resource.management.dto.SuperordinatePrivilegeDto;
import com.invicta.human.resource.management.enums.RestApiResponseStatus;
import com.invicta.human.resource.management.response.BasicResponse;
import com.invicta.human.resource.management.services.DesignationService;
import com.invicta.human.resource.management.services.RolePermissionService;
import com.invicta.human.resource.management.utils.Constants;
import com.invicta.human.resource.management.utils.EndPointURI;

@CrossOrigin(origins = "*")
@RestController
public class RolePermissionController {
  @Autowired
  private RolePermissionService rolePermissionService;
  @Autowired
  private DesignationService designationService;
  private static final Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

  @GetMapping(value = EndPointURI.ROLE_PERMISSION_BY_ROLE)
  public ResponseEntity<Object> getRolePermissionByRoleId(@PathVariable Long id) {
    logger.info("Get Role Permission by role Id {}", id);
    List<SuperordinatePrivilegeDto> superordinatePrivilegeDtos;
    superordinatePrivilegeDtos = rolePermissionService.getPrivilegeByRoleId(id);
    return new ResponseEntity<>(superordinatePrivilegeDtos, HttpStatus.OK);
  }

  @PostMapping(value = EndPointURI.ROLE_PERMISSION)
  public ResponseEntity<Object> saveAndUpdateRolePermission(
      @RequestBody RolePermissionDto permissionDto) {
    if (!designationService.isIdExists(permissionDto.getRoleId())) {
      logger.info("Role id {} not exsist", permissionDto.getRoleId());
      return new ResponseEntity<>(Constants.PERMISSIONS, HttpStatus.BAD_REQUEST);
    }
    if (rolePermissionService.isSameCollectionOfPermissions(permissionDto)) {
      logger.info("Role id {} Permission Already insert", permissionDto.getRoleId());
      return new ResponseEntity<>(Constants.PERMISSIONS, HttpStatus.BAD_REQUEST);
    }
    rolePermissionService.saveAndUpdateRolePermission(permissionDto);
    logger.info("RolePermission Add Success");
    return new ResponseEntity<>(
        new BasicResponse(RestApiResponseStatus.OK, Constants.ROLE_PERMISSIONS_ADD_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndPointURI.SIGN_IN_ROLE_PERMISSION)
  public ResponseEntity<Object> getSignInPermission(Principal principal) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        rolePermissionService.getPrivilegeByUsername(principal.getName()),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
  @GetMapping(value = EndPointURI.GET_ROLE_PERMISSION)
  public ResponseEntity<Object> getRolePermission(@PathVariable Long id) {
    logger.info("Get Role Permission by role Id {}", id);
    RolePermissionDto rolePermissionDto = rolePermissionService.getPrivilegesByRoleId(id);
    return new ResponseEntity<>(rolePermissionDto, HttpStatus.OK);
  }

}
