package com.sofkau.saint_claire.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Pacient {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(updatable = false, length = 45, nullable = false)
  private String name;

  @Column(updatable = false, nullable = false)
  private Integer age;

  @Column(updatable = false, nullable = false)
  private Long identiticationNumber;

  @Column(columnDefinition = "TEXT")
  private String datesAppointments;

  @Transient
  private List<String> datesAppointmentsList = new ArrayList<>();

  private Long numberOfAppointments;

  public Pacient() {
  }

  public Pacient(String name, Integer age, Long identiticationNumber) {
    this.name = name;
    this.age = age;
    this.identiticationNumber = identiticationNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
  public Long getIdentiticationNumber() {
    return identiticationNumber;
  }

  public void setIdentiticationNumber(Long identiticationNumber) {
    this.identiticationNumber = identiticationNumber;
  }

  public List<String> getDatesAppointmentsList() {
    return datesAppointmentsList;
  }

  public void setDatesAppointmentsList(List<String> datesAppointmentsList) {
    this.datesAppointmentsList = datesAppointmentsList;
  }

  public Long getNumberOfAppointments() {
    return numberOfAppointments;
  }

  public void setNumberOfAppointments(Long numberOfAppointments) {
    this.numberOfAppointments = numberOfAppointments;
  }

  public String getDatesAppointments() {
    return datesAppointments;
  }

  public void setDatesAppointments(String datesAppointments) {
    this.datesAppointments = datesAppointments;
  }
}
