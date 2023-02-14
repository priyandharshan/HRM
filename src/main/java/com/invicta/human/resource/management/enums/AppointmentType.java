package com.invicta.human.resource.management.enums;

public enum AppointmentType {
  INTERN("Intern"), TEMPORARY("Temporary"), FIXED("Fixed");

  private String type;

  AppointmentType(String type) {
    this.type = type;
  }
}
