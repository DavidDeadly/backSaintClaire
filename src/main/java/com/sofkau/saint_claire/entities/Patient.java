package com.sofkau.saint_claire.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Patient {
  @Id
  @SequenceGenerator(
          name = "pacient_sequence",
          sequenceName = "pacient_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "pacient_sequence"
  )
  private Long id;

  @Column(updatable = false, length = 45, nullable = false)
  private String name;

  @Column(updatable = false, nullable = false)
  private Integer age;

  @Column(updatable = false, nullable = false, unique = true)
  private Long identiticationNumber;

  @Column(columnDefinition = "TEXT")
  private String datesAppointments;

  private Long numberOfAppointments;


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "specialty_id", referencedColumnName = "id")
  @JsonBackReference
  private Specialty specialty;

  public Patient() {
  }

  public Patient(Long id, String name, Integer age, Long identificationNumber, String datesAppointments, Long numberOfAppointments) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.identiticationNumber = identificationNumber;
    this.datesAppointments = datesAppointments;
    this.numberOfAppointments = numberOfAppointments;
  }

  public Patient(String name, Integer age, Long identiticationNumber) {
    this.name = name;
    this.age = age;
    this.identiticationNumber = identiticationNumber;
    this.numberOfAppointments = 0L;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public Long getIdentiticationNumber() {
    return identiticationNumber;
  }

  public List<String> getDatesAppointments() {
    String strDates = datesAppointments;
    if(strDates == null) {
      return new ArrayList<>();
    }
    return new ArrayList<>(List.of(strDates.split(";")));
  }

  public void setDatesAppointments(String date) {
    List<String> dates = getDatesAppointments();
    dates.add(date);
    this.datesAppointments = String.join(";", dates);
  }

  public Long getNumberOfAppointments() {
    return (long) getDatesAppointments().size();
  }

  public void setNumberOfAppointments(Long numberOfAppointments) {
    this.numberOfAppointments = numberOfAppointments;
  }

  public Specialty getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Specialty specialty) {
    this.specialty = specialty;
  }
}
