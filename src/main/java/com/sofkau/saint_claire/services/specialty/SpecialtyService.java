package com.sofkau.saint_claire.services.specialty;

import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.entities.Specialty;
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
  private PatientService pacientService;

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
    Patient patient = pacientService.addPatientDate(patientId, date);
    patient.setSpecialty(specialty);
    specialty.getPatients().add(patient);
    return specialty;
  }
}
