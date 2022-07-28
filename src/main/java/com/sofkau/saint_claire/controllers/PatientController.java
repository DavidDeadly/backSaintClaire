package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.pacient.PatientDTO;
import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.services.pacient.PatientService;
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
}
