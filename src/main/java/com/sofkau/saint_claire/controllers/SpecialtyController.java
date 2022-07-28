package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.entities.Specialty;
import com.sofkau.saint_claire.services.specialty.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

  @Autowired
  private SpecialtyService specialtyService;

  @GetMapping
  public List<Specialty> getSpecialties() {
    return specialtyService.getSpecialties();
  }

  @PutMapping(path = "{specialtyId}")
  public void addPacient(
          @PathVariable("specialtyId") Long specialtyId,
          @RequestParam(name = "patientId") Long patientId,
          @RequestParam(name = "date") String date
  ) {
    specialtyService.addPatient(specialtyId, patientId, date);
  }

}
