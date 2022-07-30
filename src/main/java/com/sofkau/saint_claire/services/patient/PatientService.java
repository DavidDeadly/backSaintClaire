package com.sofkau.saint_claire.services.patient;

import com.sofkau.saint_claire.dto.Mapper;
import com.sofkau.saint_claire.dto.patient.PatientDTO;
import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.errors.exceptions.CreationEntityException;
import com.sofkau.saint_claire.errors.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
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
    if (byId.isEmpty()) throw new NotFoundEntityException("That patient doesn't exist");
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
    if(nameLen < 10 || nameLen > 45) throw new CreationEntityException("Patient name must be between 10 & 45 characters");

    if(patient.age <= 0) throw new CreationEntityException("Patient age can't be zero or less");
  }

  public Patient deletePatient(Long id) {
    Patient patient = getPatient(id);
    patientRepository.delete(patient);
    return patient;
  }

  @Transactional
  public Patient deletePatientAppointments(Long id, int newSize, boolean reverse) {
    Patient patient = getPatient(id);
    if(newSize <= 0) patient.setSpecialty(null);

    if (!reverse) patient.setNumberOfAppointments(newSize);
    else patient.setNumberOfAppointments((long) newSize);

    return patient;
  }
}
