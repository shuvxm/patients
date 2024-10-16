package com.example.patients.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.patients.entity.Patient;

public interface PatientsRepository extends JpaRepository<Patient, Long>{

}
