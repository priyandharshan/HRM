package com.invicta.human.resource.management.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.invicta.human.resource.management.dto.SuperordinatePrivilegeDto;
import com.invicta.human.resource.management.services.PermissionService;
import com.invicta.human.resource.management.utils.EndPointURI;

@CrossOrigin(origins = "*")
@RestController
public class PermissionController {
  @Autowired
  private PermissionService permissionService;

  private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

  @GetMapping(value = EndPointURI.PERMISSION)
  public ResponseEntity<Object> getAllPermission() {
    logger.info("Get all permission");
    List<SuperordinatePrivilegeDto> permission;
    permission = permissionService.getAllPermission();
    return new ResponseEntity<>(permission, HttpStatus.OK);
  }
}
