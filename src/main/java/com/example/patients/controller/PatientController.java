package com.example.patients.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.patients.entity.Patient;
import com.example.patients.repository.PatientsRepository;
import com.example.patients.responsestructure.ResponseStructure;

@RestController
public class PatientController {

	@Autowired
	private PatientsRepository patientRepository;
	
	@PostMapping("/persons")
	public ResponseStructure<Patient> savePerson(@RequestBody Patient patient) {
		patientRepository.save(patient);
		
		ResponseStructure<Patient> structure = new ResponseStructure<>();
		structure.setStatus(201);
		structure.setMessage("Person saved successfully");
		structure.setBody(patient);
		
		return structure;
	}
	
	@GetMapping("/persons")
	public ResponseStructure<List<Patient>> findAllPersons(){
		List<Patient> pl = patientRepository.findAll();
		ResponseStructure<List<Patient>> structure = new ResponseStructure<>();
		if(pl.isEmpty()) {
			structure.setStatus(400);
			structure.setMessage("No person found in db");
			structure.setBody(null);
			return structure;
		}
		else {
			structure.setStatus(200);
			structure.setMessage("All Person found successfully..");
			structure.setBody(pl);
			
			return structure;
		}
		
		
	}
	
	@GetMapping("persons/{id}")
	public ResponseStructure<Patient> findPersonByID(@PathVariable long id) {
		Optional<Patient> optional = patientRepository.findById(id);
		ResponseStructure<Patient> structure = new ResponseStructure<>();
		
		if(optional.isPresent()) {
			Patient p = optional.get();
			structure.setStatus(200);
			structure.setMessage("Person found");
			structure.setBody(p);
			return structure;
		}
		else
		{	
			structure.setStatus(404);
			structure.setMessage("Invalid person id..Person not found");
			structure.setBody(null);
			return structure;
		}
			
	}
	@DeleteMapping("/persons/{id}")
	public ResponseStructure<String> deletePersonById(@PathVariable long id) {
		
		Optional<Patient> op = patientRepository.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		if(op.isPresent()) {
			patientRepository.deleteById(id);
			structure.setStatus(200);
			structure.setMessage("deleted successfully");
			structure.setBody("person deleted");
			return structure;
		}
		else {
			structure.setStatus(404);
			structure.setMessage("Invalid Person ID");
			structure.setBody("Unable to delete person");
			return structure;
		}
	}
	@PutMapping("/persons")
	public ResponseStructure<Patient> updatePerson(@RequestBody Patient patient){
		patientRepository.save(patient);
		ResponseStructure<Patient> structure = new ResponseStructure<>();
		structure.setStatus(200);
		structure.setMessage("Person updated successfully");
		structure.setBody(patient);
		return structure;
	}
	

}
