package com.invicta.human.resource.management.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LoginAuthenticationController {

  @GetMapping("/login")
  public String login() {
    return "Successfully Login";
  }

}
