package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EmployeeProfileImageDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmployeeProfileImageService {

  void saveProfileImage(MultipartFile file, String id) throws IOException;

  Resource load(String fileName, String id) throws RuntimeException;

  void deleteImage(String employeeId);

  void updateImage(MultipartFile file, String id) throws RuntimeException;

  void deleteProfileImageFromRootFolder(String fileName);

  boolean isSameFileName(String fileName, String id);

  boolean fileExists(String fileName);

  EmployeeProfileImageDto getImageDetails(String id);

  boolean existsByEmployeeId(String employeeId);

  EmployeeProfileImageDto saveImageToFolder(MultipartFile multipartFile);
}
