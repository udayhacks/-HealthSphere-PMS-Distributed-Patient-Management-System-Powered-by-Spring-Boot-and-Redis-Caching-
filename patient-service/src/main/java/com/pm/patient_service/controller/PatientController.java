package com.pm.patient_service.controller;


import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public  ResponseEntity<List<PatientResponseDTO>> getpatient(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping("/newpatient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){

        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                               @Valid @RequestBody PatientRequestDTO patientRequestDTO) {

        return ResponseEntity.ok().body(patientService.updatePatient( id,patientRequestDTO));
    }




}
