package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.EmployeeProfileImageDto;
import com.invicta.human.resource.management.entities.Employee;
import com.invicta.human.resource.management.entities.EmployeeProfileImage;
import com.invicta.human.resource.management.repositories.EmployeeProfileImageRepository;
import com.invicta.human.resource.management.repositories.EmployeeRepository;
import com.invicta.human.resource.management.utils.PropertyFileValue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
class EmployeeProfileImageServiceImplementation implements EmployeeProfileImageService {

  @Autowired
  private PropertyFileValue propertyFileValue;
  @Autowired
  private EmployeeProfileImageRepository employeeProfileImageRepository;
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * Save image
   *
   * @param profileImage multipart Image
   * @param employeeId   String employeeId Modified by @author priyadharshan on 2022-11-15
   */
  @Override
  public void saveProfileImage(MultipartFile profileImage, String employeeId) {
    EmployeeProfileImage employeeProfileImage = new EmployeeProfileImage();
    EmployeeProfileImageDto employeeProfileImageDto = saveImageToFolder(profileImage);
    BeanUtils.copyProperties(employeeProfileImageDto, employeeProfileImage);
    Employee employee =new Employee();
    employee.setId(employeeId);
    employeeProfileImage.setEmployee(employee);
    employeeProfileImageRepository.save(employeeProfileImage);
  }

  /**
   * Load image
   *
   * @param fileName   image Name
   * @param employeeId String Modified by @author priyadharshan on 2022-11-15
   */
  @Override
  public Resource load(String fileName, String employeeId) {
    Path file = propertyFileValue.getRoot().resolve(fileName);

    try {
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Delete Image
   *
   * @param employeeId Modified by @author priyadharshan on 2022-11-15
   */
  @Override
  public void deleteImage(String employeeId) {
    EmployeeProfileImage employeeProfileImage =
        employeeProfileImageRepository.getByEmployeeId(employeeId);
    File file = new File(employeeProfileImage.getFileUrl());
    if (fileExists(file.getName())) {
      deleteProfileImageFromRootFolder(employeeProfileImage.getFileName());
    }
    employeeProfileImageRepository.deleteById(employeeProfileImage.getId());
  }

  /**
   * Delete Image
   *
   * @param profileImage
   * @param employeeId   Modified by @author priyadharshan on 2022-11-14
   */
  @Override
  public void updateImage(MultipartFile profileImage, String employeeId) {
    EmployeeProfileImage employeeProfileImage =
        employeeProfileImageRepository.findByEmployeeId(employeeId);
    EmployeeProfileImageDto employeeProfileImageDto = saveImageToFolder(profileImage);
    employeeProfileImageDto.setId(employeeProfileImage.getId());
    BeanUtils.copyProperties(employeeProfileImageDto, employeeProfileImage);
    employeeProfileImageRepository.save(employeeProfileImage);
  }

  /**
   * Delete Image from folder
   *
   * @param fileName Modified by @author priyadharshan on 2022-11-15
   */

  @Override
  public void deleteProfileImageFromRootFolder(String fileName) {

    if (fileName != null) {
      try {
        Files.delete(propertyFileValue.getRoot().resolve(fileName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Check File name is same to the employee
   *
   * @param fileName
   * @param employeeId Modified by @author priyadharshan on 2022-11-11
   */
  @Override
  public boolean isSameFileName(String fileName, String employeeId) {
    EmployeeProfileImage employeeProfileImage =
        employeeProfileImageRepository.findByEmployeeId(employeeId);
    return !Objects.equals(employeeProfileImage.getFileName(), fileName);
  }

  /**
   * Check file exists in the folder
   *
   * @param fileName
   * @return boolean Modified by @author priyadharshan on 2022-11-15
   */
  @Override
  public boolean fileExists(String fileName) {
    return Files.exists(propertyFileValue.getRoot().resolve(fileName));
  }

  /**
   * Get Image Details
   *
   * @param employeeId
   * @return employeeProfileDto Modified by @author priyadharshan on 2022-11-15
   */

  @Override
  public EmployeeProfileImageDto getImageDetails(String employeeId) {
    EmployeeProfileImage employeeProfileImage =
        employeeProfileImageRepository.findByEmployeeId(employeeId);
    EmployeeProfileImageDto employeeProfileImageDto = new EmployeeProfileImageDto();
    BeanUtils.copyProperties(employeeProfileImage, employeeProfileImageDto);
    return employeeProfileImageDto;
  }

  /**
   * Check employeeId Exists
   *
   * @param employeeId
   * @return boolean Modified by @author priyadharshan on 2022-11-14
   */
  @Override
  public boolean existsByEmployeeId(String employeeId) {
    return employeeProfileImageRepository.existsByEmployeeId(employeeId);
  }

  /**
   * Save Image to the folder
   *
   * @param multipartFile
   * @return employeeProfileImageDto Modified by @author priyadharshan on 2022-11-15
   */

  @Override
  public EmployeeProfileImageDto saveImageToFolder(MultipartFile multipartFile) {
    EmployeeProfileImageDto employeeProfileImageDto = new EmployeeProfileImageDto();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd-HH-mm-ss");
    File newFile = new File(
        simpleDateFormat.format(new Date().getTime()) + multipartFile.getOriginalFilename());
    String fileUrl =
        File.separator + propertyFileValue.getRoot() + File.separator + newFile.getName();
    try {
      Files.copy(multipartFile.getInputStream(),
          this.propertyFileValue.getRoot().resolve(newFile.getName()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    employeeProfileImageDto.setFileName(newFile.getName());
    employeeProfileImageDto.setFileUrl(fileUrl);
    employeeProfileImageDto.setFileType(multipartFile.getContentType());
    return employeeProfileImageDto;
  }

}
