package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.specialty.SpecialtyDTO;
import com.sofkau.saint_claire.services.specialty.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {

  @Autowired
  private SpecialtyService specialtyService;

  @GetMapping
  public ResponseEntity<List<SpecialtyDTO>> getSpecialties() {
    return new ResponseEntity<>(
      specialtyService.getSpecialties()
        .stream()
        .map(Mapper::createSpecialtyDTO)
        .toList(),
      HttpStatus.OK
    );
  }

  @PutMapping(path = "{specialtyId}")
  public ResponseEntity<SpecialtyDTO> addPatient(
          @PathVariable("specialtyId") Long specialtyId,
          @RequestParam(name = "patientId") Long patientId,
          @RequestParam(name = "date") String date
  ) {
    return new ResponseEntity<>(
      Mapper.createSpecialtyDTO(
        specialtyService.addPatient(specialtyId, patientId, date)
      ),
      HttpStatus.CREATED
    );
  }

}
