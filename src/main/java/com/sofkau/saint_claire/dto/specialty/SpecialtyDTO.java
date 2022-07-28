package com.sofkau.saint_claire.dto.specialty;

import com.sofkau.saint_claire.dto.patient.PatientDTO;

import java.util.Set;

public class SpecialtyDTO {
  public final Long id;
  public final String name;
  public final String physicianInCharge;
  public final Set<PatientDTO> patients;

  public SpecialtyDTO(Long id, String name, String physicianInCharge, Set<PatientDTO> patients) {
    this.id = id;
    this.name = name;
    this.physicianInCharge = physicianInCharge;
    this.patients = patients;
  }
}
