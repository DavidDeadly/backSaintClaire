package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.patient.PatientDTO;
import com.sofkau.saint_claire.errors.InvalidRequest;
import com.sofkau.saint_claire.services.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @GetMapping
  public ResponseEntity<List<PatientDTO>> getPatients() {
    return new ResponseEntity<>(
      patientService.getPatients()
        .stream()
        .map(Mapper::createPatientDTO)
        .toList(),
      HttpStatus.OK
    );
  }

  @GetMapping(path = "{patientId}")
  public ResponseEntity<PatientDTO> getPatient(@PathVariable(name = "patientId") Long id) {
    return new ResponseEntity<>(
      Mapper.createPatientDTO(
        patientService.getPatient(id)
      ),
      HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patient) {
    if (
      patient.name == null ||
      patient.age == null ||
      patient.identificationNumber == null
    ) throw new InvalidRequest("name, age & identification number are required for patient creation");

    return new ResponseEntity<>(
      Mapper.createPatientDTO(
        patientService.createPatient(patient)
      ),
      HttpStatus.CREATED
    );
  }

  @PutMapping(path = "{patientId}")
  public ResponseEntity<PatientDTO> deletePatientAppointments(
          @PathVariable(name = "patientId") Long id,
          @RequestParam(name = "newLen", defaultValue = "0") int newSize,
          @RequestParam(name = "lastOnes", required = false, defaultValue = "true") boolean reverse
  ) {
    return new ResponseEntity<>(
      Mapper.createPatientDTO(
        patientService.deletePatientAppointments(id, newSize, reverse)
      ),
      HttpStatus.ACCEPTED
    );
  }

  @DeleteMapping(path = "{patientId}")
  public ResponseEntity<PatientDTO> deletePatient(@PathVariable(name = "patientId") Long id) {
    return new ResponseEntity<>(
      Mapper.createPatientDTO(
        patientService.deletePatient(id)
      ),
     HttpStatus.OK
    );
  }
}
