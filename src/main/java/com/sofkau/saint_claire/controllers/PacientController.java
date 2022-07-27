package com.sofkau.saint_claire.controllers;

import com.sofkau.saint_claire.entities.Pacient;
import com.sofkau.saint_claire.services.pacient.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pacient")
public class PacientController {

  @Autowired
  private PacientService pacientService;

  @GetMapping
  public List<Pacient> helloWorld(){
    return pacientService.getPacients();
  }
}
