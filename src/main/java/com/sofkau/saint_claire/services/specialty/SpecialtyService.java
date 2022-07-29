package com.sofkau.saint_claire.services.specialty;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.specialty.SpecialtyDTO;
import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.entities.Specialty;
import com.sofkau.saint_claire.errors.InvalidRequest;
import com.sofkau.saint_claire.services.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SpecialtyService {
  @Autowired
  private SpecialtyRepository specialtyRepository;

  @Autowired
  private PatientService patientService;

  public List<Specialty> getSpecialties() {
    return specialtyRepository.findAll();
  }

  public Specialty getSpecialty(Long id) {
    Optional<Specialty> byId = specialtyRepository.findById(id);
    if(byId.isEmpty()) throw new IllegalStateException("This specialty doesn't exist");
    return byId.get();
  }

  @Transactional
  public Specialty addPatient(Long specialtyId, Long patientId, String date) {
    Optional<Specialty> byId = specialtyRepository.findById(specialtyId);
    if(byId.isEmpty()) throw new IllegalStateException("This specialty doesn't exist");
    Specialty specialty = byId.get();
    Patient patient = patientService.addPatientDate(patientId, date);
    patient.setSpecialty(specialty);
    specialty.getPatients().add(patient);
    return specialty;
  }

  @Transactional
  public Specialty updateSpecialty(Long specialtyId, String name, String physicianInCharge) {
    Specialty specialty = getSpecialty(specialtyId);
    if(name != null) {
      checkSpecialtyName(name);
      specialty.setName(name);
    }

    if(physicianInCharge != null) {
      checkSpecialtyPhysician(physicianInCharge);
      specialty.setPhysicianInCharge(physicianInCharge);
    }

    return specialty;
  }

  public Specialty createSpecialty(SpecialtyDTO specialty) {
    checkSpecialtyName(specialty.name);
    checkSpecialtyPhysician(specialty.physicianInCharge);
    return specialtyRepository.save(
        Mapper.createSpecialtyEntity(specialty)
    );
  }

  private void checkSpecialtyName(String name) {
    int nameLen = name.length();
    if(nameLen < 5 || nameLen > 100) throw new InvalidRequest("Specialty name must be between 5 & 100 characters");
  }

  private void checkSpecialtyPhysician(String physicianInCharge) {
    int nameLen = physicianInCharge.length();
    if(nameLen < 10 || nameLen > 45) throw new InvalidRequest("Specialty name must be between 5 & 100 characters");
  }

  public Specialty deleteSpecialty(Long specialtyId) {
    Specialty specialty = getSpecialty(specialtyId);
    int quantityPatients = specialty.getPatients().size();
    if(quantityPatients > 0) throw new InvalidRequest("This specialty still have patients");
    specialtyRepository.delete(specialty);
    return specialty;
  }
}
