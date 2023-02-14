package com.invicta.human.resource.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyRegistryDto {
  private String employeeId;
  private long id;
  private String keyNumber;
  private boolean keyAvailable; 

}
