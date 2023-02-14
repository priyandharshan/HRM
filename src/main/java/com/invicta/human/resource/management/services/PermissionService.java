package com.invicta.human.resource.management.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.invicta.human.resource.management.dto.SuperordinatePrivilegeDto;

@Service
public interface PermissionService {

  List<SuperordinatePrivilegeDto> getAllPermission();

}
