package com.sofkau.saint_claire.services.pacient;

import com.sofkau.saint_claire.entities.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
}
