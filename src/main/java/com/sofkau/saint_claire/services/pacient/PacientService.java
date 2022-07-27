package com.sofkau.saint_claire.services.pacient;

import com.sofkau.saint_claire.entities.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientService {
  @Autowired
  private PacientRepository pacientRepository;

  public List<Pacient> getPacients() {
    return pacientRepository.findAll();
  }
}
