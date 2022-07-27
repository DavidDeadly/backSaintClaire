package com.sofkau.saint_claire.services.specialty;

import com.sofkau.saint_claire.entities.Pacient;
import com.sofkau.saint_claire.entities.Specialty;
import com.sofkau.saint_claire.services.pacient.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SpecialtyService {
  @Autowired
  private SpecialtyRepository specialtyRepository;

  @Autowired
  private PacientService pacientService;

  public List<Specialty> getSpecialties() {
    return specialtyRepository.findAll();
  }

  @Transactional
  public void addPacient(Long specialtyId, Long pacientId) {
    Optional<Specialty> byId = specialtyRepository.findById(specialtyId);
    Pacient pacient = pacientService.getPacient(pacientId);
    if(byId.isEmpty()) throw new IllegalStateException("That repository doesn't exist");
    Specialty specialty = byId.get();
    Set<Pacient> pacients = specialty.getPacients();
    pacients.add(pacient);
    specialty.setPacients(pacients);
  }
}
