package com.sofkau.saint_claire.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Specialty {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 45)
  private String physicianInCharge;

  @ManyToMany
  private Set<Pacient> pacients = new HashSet<>();

  public Specialty() {
  }

  public Specialty(String name, String physicianInCharge) {
    this.name = name;
    this.physicianInCharge = physicianInCharge;
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

  public String getPhysicianInCharge() {
    return physicianInCharge;
  }

  public void setPhysicianInCharge(String physicianInCharge) {
    this.physicianInCharge = physicianInCharge;
  }

  public Set<Pacient> getPacients() {
    return pacients;
  }

  public void setPacients(Set<Pacient> pacients) {
    this.pacients = pacients;
  }
}
