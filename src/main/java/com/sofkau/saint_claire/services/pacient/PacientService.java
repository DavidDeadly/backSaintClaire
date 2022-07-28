package com.sofkau.saint_claire.services.pacient;

import com.sofkau.saint_claire.entities.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PacientService {
  @Autowired
  private PacientRepository pacientRepository;

  public List<Pacient> getPacients() {
    return pacientRepository.findAll();
  }

  public Pacient getPacient(Long id) {
    Optional<Pacient> byId = pacientRepository.findById(id);
    if (byId.isEmpty()) throw new IllegalStateException("That pacient doesn't exist");
    return byId.get();
  }

  public Pacient addPacientDate(Long pacientId, String date) {
    Pacient pacient = getPacient(pacientId);
    pacient.setNumberOfAppointments(pacient.getNumberOfAppointments() + 1);
    pacient.setDatesAppointments(date);
    return pacient;
  }
}
