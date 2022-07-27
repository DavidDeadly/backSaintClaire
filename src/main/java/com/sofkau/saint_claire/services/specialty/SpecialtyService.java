package com.sofkau.saint_claire.services.specialty;

import com.sofkau.saint_claire.entities.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {
  @Autowired
  private SpecialtyRepository specialtyRepository;

  public List<Specialty> getSpecialties() {
    return specialtyRepository.findAll();
  }
}
