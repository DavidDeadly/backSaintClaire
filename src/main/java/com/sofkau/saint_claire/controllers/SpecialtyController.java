package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.pacient.PatientDTO;
import com.sofkau.saint_claire.dto.specialty.SpecialtyDTO;
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
  public List<SpecialtyDTO> getSpecialties() {
    return specialtyService.getSpecialties()
            .stream()
            .map(Mapper::createSpecialtyDTO)
            .toList();
  }

  @PutMapping(path = "{specialtyId}")
  public SpecialtyDTO addPatient(
          @PathVariable("specialtyId") Long specialtyId,
          @RequestParam(name = "patientId") Long patientId,
          @RequestParam(name = "date") String date
  ) {
    return Mapper.createSpecialtyDTO(
            specialtyService.addPatient(specialtyId, patientId, date)
    );
  }

}
