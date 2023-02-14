package com.invicta.human.resource.management.enums;

public enum MaritialStatus {
  Married("Married"), Single("Single"), Others("Others");

  private String type;

  MaritialStatus(String type) {
    this.type = type;
  }

}
