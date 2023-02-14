package com.invicta.human.resource.management.config;

import com.invicta.human.resource.management.utils.PropertyFileValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class FolderCreation {
  @Autowired
  private PropertyFileValue propertyFileValue;

  @Bean
  public void uploadDirectoryCreate() {
    try {
      File directory = new File(String.valueOf(propertyFileValue.getRoot()));
      if (!directory.exists()) {
        Files.createDirectories(propertyFileValue.getRoot());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

