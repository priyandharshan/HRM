package com.invicta.human.resource.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaAuditing
@EnableAutoConfiguration
public class HumanResourceManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(HumanResourceManagementApplication.class, args);
  }
}
