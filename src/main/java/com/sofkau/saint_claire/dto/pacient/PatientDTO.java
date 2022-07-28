package com.sofkau.saint_claire.dto.pacient;

import java.util.List;

public class PatientDTO {
  public final Long id;
  public final String name;
  public final Integer age;
  public final Long identificationNumber;
  public final List<String> datesAppointments;
  public final Long numberOfAppointments;

  public PatientDTO(Long id, String name, Integer age, Long identificationNumber, List<String> datesAppointments, Long numberOfAppointments) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.identificationNumber = identificationNumber;
    this.datesAppointments = datesAppointments;
    this.numberOfAppointments = numberOfAppointments;
  }
}
