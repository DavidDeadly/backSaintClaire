package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.specialty.SpecialtyDTO;
import com.sofkau.saint_claire.errors.exceptions.InvalidRequestException;
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

  @GetMapping(path = "{specialtyId}")
  public ResponseEntity<SpecialtyDTO> getSpecialty(@PathVariable(name = "specialtyId") Long id) {
    return new ResponseEntity<>(
      Mapper.createSpecialtyDTO(
        specialtyService.getSpecialty(id)
      ),
      HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<SpecialtyDTO> createSpecialty(@RequestBody SpecialtyDTO specialty) {
    if (
      specialty.name == null ||
      specialty.physicianInCharge == null
    ) throw new InvalidRequestException("Specialty & Physician name are required for specialty creation");
    return new ResponseEntity<>(
      Mapper.createSpecialtyDTO(
        specialtyService.createSpecialty(specialty)
      ),
      HttpStatus.CREATED
    );
  }

  @PutMapping(path = "/update/{specialtyId}")
  public ResponseEntity<SpecialtyDTO> updateSpecialty(
          @PathVariable("specialtyId") Long specialtyId,
          @RequestParam(name = "name", required = false) String name,
          @RequestParam(name = "pic", required = false) String physicianInCharge
  ) {
    return new ResponseEntity<>(
      Mapper.createSpecialtyDTO(
        specialtyService.updateSpecialty(specialtyId, name, physicianInCharge)
      ),
      HttpStatus.ACCEPTED
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

  @DeleteMapping(path = "{specialtyId}")
  public ResponseEntity<SpecialtyDTO> deleteSpecialty(@PathVariable("specialtyId") Long specialtyId) {
    return new ResponseEntity<>(
      Mapper.createSpecialtyDTO(
          specialtyService.deleteSpecialty(specialtyId)
      ),
      HttpStatus.OK
    );
  }
}
