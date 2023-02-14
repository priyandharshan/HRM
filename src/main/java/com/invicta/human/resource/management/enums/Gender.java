package com.invicta.human.resource.management.enums;

public enum Gender {
  Male("Male"), Female("Female"), Other("Other");

  private String type;

  Gender(String type) {
    this.type = type;
  }
}
