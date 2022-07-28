package com.sofkau.saint_claire.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table
public class Pacient {
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

  @Column(updatable = false, nullable = false)
  private Long identiticationNumber;

  @Column(columnDefinition = "TEXT")
  private String datesAppointments;

  private Long numberOfAppointments;


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "specialty_id", referencedColumnName = "id")
  @JsonBackReference
  private Specialty specialty;

  public Pacient() {
  }

  public Pacient(String name, Integer age, Long identiticationNumber) {
    this.name = name;
    this.age = age;
    this.identiticationNumber = identiticationNumber;
    this.numberOfAppointments = 0L;
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

  public String getDatesAppointments() {
    return datesAppointments;
  }

  public void setDatesAppointments(String datesAppointments) {
    this.datesAppointments = datesAppointments;
  }

  public Long getNumberOfAppointments() {
    return numberOfAppointments;
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
