package com.sofkau.saint_claire.dto;

import com.sofkau.saint_claire.dto.pacient.PatientDTO;
import com.sofkau.saint_claire.dto.specialty.SpecialtyDTO;
import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.entities.Specialty;

import java.util.stream.Collectors;

public final class Mapper {

  private Mapper(){
  }

  public static SpecialtyDTO createSpecialtyDTO(Specialty specialty) {
    return new SpecialtyDTO(
            specialty.getId(),
            specialty.getName(),
            specialty.getPhysicianInCharge(),
            specialty.getPatients()
                    .stream()
                    .map(Mapper::createPatientDTO)
                    .collect(Collectors.toSet())
    );
  }

  public static Specialty createSpecialtyEntity(SpecialtyDTO specialty) {
    return new Specialty(
            specialty.id,
            specialty.name,
            specialty.physicianInCharge
    );
  }

  public static PatientDTO createPatientDTO(Patient patient) {
    return new PatientDTO(
            patient.getId(),
            patient.getName(),
            patient.getAge(),
            patient.getIdentiticationNumber(),
            patient.getDatesAppointments(),
            patient.getNumberOfAppointments()
    );
  }

  public static Patient createPatientEntity(PatientDTO patientDTO) {
    return new Patient(
            patientDTO.id,
            patientDTO.name,
            patientDTO.age,
            patientDTO.identificationNumber,
            String.join(";", patientDTO.datesAppointments),
            patientDTO.numberOfAppointments
    );
  }
}
