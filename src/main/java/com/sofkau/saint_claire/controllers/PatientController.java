package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.patient.PatientDTO;
import com.sofkau.saint_claire.errors.InvalidRequest;
import com.sofkau.saint_claire.services.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @GetMapping
  public List<PatientDTO> getPatients() {
    return patientService.getPatients()
            .stream()
            .map(Mapper::createPatientDTO)
            .toList();
  }

  @GetMapping(path = "{patientId}")
  public PatientDTO getPatient(@PathVariable(name = "patientId") Long id) {
    return Mapper.createPatientDTO(
            patientService.getPatient(id)
    );
  }

  @PostMapping
  public PatientDTO createPatient(@RequestBody PatientDTO patient) {
    if(
      patient.name == null ||
      patient.age == null ||
      patient.identificationNumber == null
    ) throw new InvalidRequest("name, age & identification number are required for patient creation");

    return Mapper.createPatientDTO(
            patientService.createPatient(patient)
    );
  }

  @PutMapping(path = "{patientId}")
  public PatientDTO deletePatientAppointments(
          @PathVariable(name = "patientId") Long id,
          @RequestParam(name = "newLen", defaultValue = "0") int newSize,
          @RequestParam(name = "lastOnes", required = false, defaultValue = "true") boolean reverse
  ) {
    return Mapper.createPatientDTO(
        patientService.deletePatientAppointments(id, newSize, reverse)
    );
  }
  
  @DeleteMapping(path = "{patientId}")
  public PatientDTO deletePatient(@PathVariable(name = "patientId") Long id) {
    return Mapper.createPatientDTO(
            patientService.deletePatient(id)
    );
  }
}
