package com.pm.patient_service.controller;


import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.service.PatientService;
import com.pm.patient_service.validators.CreatePatientValidationGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/patient")
@Tag(name="Patient",description = "patient route")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    @Operation(summary = "Get patient list")
    public  ResponseEntity<List<PatientResponseDTO>>
    getpatient(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping("/newpatient")
     @Operation(summary = "Create new Patient")
    public ResponseEntity<PatientResponseDTO>
    createPatient( @Validated({Default.class, CreatePatientValidationGroup.class})  @RequestBody PatientRequestDTO patientRequestDTO){

        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates Patient data")
    public ResponseEntity<PatientResponseDTO>
    updatePatient(@PathVariable UUID id, @Validated({Default.class }) @RequestBody PatientRequestDTO patientRequestDTO) {

        return ResponseEntity.ok().body(patientService.updatePatient( id,patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Removes Patient")
    public ResponseEntity<Void>
    deletePatient(@PathVariable UUID id){
        return ResponseEntity.noContent().build();
    }


}
