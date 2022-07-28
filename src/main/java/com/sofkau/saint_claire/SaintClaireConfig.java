package com.sofkau.saint_claire;

import com.sofkau.saint_claire.entities.Patient;
import com.sofkau.saint_claire.entities.Specialty;
import com.sofkau.saint_claire.services.patient.PatientRepository;
import com.sofkau.saint_claire.services.specialty.SpecialtyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SaintClaireConfig {

  @Bean
  CommandLineRunner commandLineRunner(
    SpecialtyRepository repository
  ) {
    return args -> {
      List<Specialty> specialties = new ArrayList<>(Arrays.asList(
              new Specialty("dermatology", "David Rueda"),
              new Specialty("pediatrics,", "Isabel Rueda"),
              new Specialty("nutrition,", "Sandra Jaramillo"),
              new Specialty("general medicine,", "Jose Zapata"),
              new Specialty("farmacy", "Jimmy Hoyos")));
      repository.saveAll(specialties);
    };
  }

  @Bean
  CommandLineRunner commandLineRunner2(
          PatientRepository repository
  ) {
    return args -> {
      List<Patient> pacients = new ArrayList<>(Arrays.asList(
              new Patient("Karlo Gomez", 19, 1000293315L),
              new Patient("Maria Cuadrado", 14, 1025891626L),
              new Patient("Susana Ochoa", 21, 213213213L),
              new Patient("Juan Marquez", 34, 1032232134L),
              new Patient("Rosa Rodriguez", 25, 21937213821L)));
      repository.saveAll(pacients);
    };
  }
}
