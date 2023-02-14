package com.invicta.human.resource.management.enums;

public enum VaccinesNames {
  Moderna("Moderna"), Pfizer("Pfizer"), Sinopharm("Sinopharm"), Sputnik_V("Sputnik_V"), AstraZeneca(
      "AstraZeneca"), Sinovac("Sinovac"), Others("Others");

  private String type;

  VaccinesNames(String type) {
    this.type = type;
  }

}
