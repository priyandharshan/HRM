package com.invicta.human.resource.management.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;


@Component
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class PropertyFileValue {
  private PropertyFileValue() {
  }
  @Value("${lms.leave.rest.call.url}")
  private String leaveManagementUrl;
  @Value("${image.attachment.fileUploadPathWindows}")
  private String fileUploadPathWindows;
  @Value("${image.attachment.fileUploadPathLinux}")
  private String fileUploadPathLinux;

  public Path getRoot() {
    if (System.getProperty("os.name").contains("Win")) {
      return Paths.get(this.fileUploadPathWindows);
    }
    return Paths.get(this.fileUploadPathLinux);
  }
}

