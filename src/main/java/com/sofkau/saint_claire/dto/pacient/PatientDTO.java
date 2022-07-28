package com.sofkau.saint_claire.dto.pacient;

import java.util.List;

public class PatientDTO {
  public Long id;
  public String name;
  public Integer age;
  public Long identificationNumber;
  public List<String> datesAppointments;
  public Long numberOfAppointments;

  public PatientDTO(Long id, String name, Integer age,  Long identificationNumber, List<String> datesAppointments, Long numberOfAppointments) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.identificationNumber = identificationNumber;
    this.datesAppointments = datesAppointments;
    this.numberOfAppointments = numberOfAppointments;
  }
}
