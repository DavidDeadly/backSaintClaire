package com.sofkau.saint_claire.services.pacient;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.pacient.PatientDTO;
import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.errors.InvalidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
  @Autowired
  private PatientRepository patientRepository;

  public List<Patient> getPatients() {
    return patientRepository.findAll();
  }

  public Patient getPatient(Long id) {
    Optional<Patient> byId = patientRepository.findById(id);
    if (byId.isEmpty()) throw new IllegalStateException("That pacient doesn't exist");
    return byId.get();
  }

  public Patient addPatientDate(Long patientId, String date) {
    Patient patient = getPatient(patientId);
    patient.setDatesAppointments(date);
    patient.setNumberOfAppointments(patient.getNumberOfAppointments());
    return patient;
  }

  public Patient createPatient(PatientDTO patient) {
    checkPatientAtrs(patient);
    return patientRepository.save(
            Mapper.createPatientEntity(patient)
    );
  }

  private void checkPatientAtrs(PatientDTO patient) {
    int nameLen = patient.name.length();
    if(nameLen < 10 || nameLen > 45) throw new InvalidRequest("Patient name must be between 10 & 45 characters");

    if(patient.age <= 0) throw new InvalidRequest("Patient age can't be zero or less");
  }
}
