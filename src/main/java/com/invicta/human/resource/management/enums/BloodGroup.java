package com.invicta.human.resource.management.enums;

public enum BloodGroup {
  A_POSITIVE("A_positive"), B_POSITIVE("B_positive"), AB_POSITIVE("AB_positive"), O_POSITIVE(
      "O_positive"), A_NEGATIVE("A_negative"), B_NEGATIVE("B_negative"), AB_NEGATIVE(
      "AB_negative"), O_NEGATIVE("O_negative");

  private String bloodType;

  BloodGroup(String bloodType) {
    this.bloodType = bloodType;
  }
}
