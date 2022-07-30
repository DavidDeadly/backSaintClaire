package com.sofkau.saint_claire.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sofkau.saint_claire.errors.exceptions.IllegalChangeException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table
public class Patient {
  @Id
  @SequenceGenerator(
          name = "patient_sequence",
          sequenceName = "patient_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "patient_sequence"
  )
  private Long id;

  @Column(updatable = false, length = 45, nullable = false)
  private String name;

  @Column(updatable = false, nullable = false)
  private Integer age;

  @Column(updatable = false, nullable = false, unique = true)
  private Long identificationNumber;

  @Column(columnDefinition = "TEXT")
  private String datesAppointments;

  private Long numberOfAppointments;


  @ManyToOne
  @JoinColumn(name = "specialty_id", referencedColumnName = "id")
  @JsonBackReference
  private Specialty specialty;

  public Patient() {
  }

  public Patient(Long id, String name, Integer age, Long identificationNumber, String datesAppointments, Long numberOfAppointments) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.identificationNumber = identificationNumber;
    this.datesAppointments = datesAppointments;
    this.numberOfAppointments = numberOfAppointments;
  }

  public Patient(String name, Integer age, Long identiticationNumber) {
    this.name = name;
    this.age = age;
    this.identificationNumber = identiticationNumber;
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

  public Long getIdentificationNumber() {
    return identificationNumber;
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
    boolean contains = dates.contains(date);
    if(!contains) dates.add(date);
    this.datesAppointments = String.join(";", dates);
  }

  public Long getNumberOfAppointments() {
    return (long) getDatesAppointments().size();
  }

  public void setNumberOfAppointments(Long numberOfAppointments) {
    if(numberOfAppointments <= 0) this.datesAppointments = null;
    else cutDatesAppointments(numberOfAppointments);
    this.numberOfAppointments = numberOfAppointments;
  }

  public void setNumberOfAppointments(Integer newSize) {
    if(newSize <= 0) this.datesAppointments = null;
    else cutDatesAppointmentsReverse(newSize);
    this.numberOfAppointments = (long ) newSize;
  }

  public Specialty getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Specialty specialty) {
    this.specialty = specialty;
  }

  private void cutDatesAppointments(Long numberOfAppointments) {
    List<String> dates = getDatesAppointments();
    if(numberOfAppointments > dates.size()) throw new IllegalChangeException("That size exceeds the current number of appointments");
    dates = dates.subList(0, Math.toIntExact(numberOfAppointments));
    this.datesAppointments = String.join(";", dates);
  }

  private void cutDatesAppointmentsReverse(Integer newSize) {
    List<String> dates = getDatesAppointments();
    if(newSize > dates.size()) throw new IllegalChangeException("That size exceeds the current number of appointments");
    Collections.reverse(dates);
    dates = dates.subList(0, newSize);
    Collections.reverse(dates);
    this.datesAppointments = String.join(";", dates);
  }


}
