package com.pm.patient_service.service;



import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.grpc.BillingServiceGrpcClient;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PatientService {
    private static final Logger log =
            LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository ,BillingServiceGrpcClient billingServiceGrpcClient) {

        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<PatientResponseDTO> getPatients(){

        List<Patient>  patients = patientRepository.findAll();
        return patients.stream().map(
                PatientMapper::toDTO).toList();

    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){


        if ( patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("a Patient email is already Exist "+ patientRequestDTO.getEmail());
        }

        Patient patient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );// these log might be removed before production
        log.info("Creating patient with email={}", patientRequestDTO.getEmail());
        // billing service is created ;
        // these log must be too ;
        log.info("Patient saved with id={}, calling billing service", patient.getId());
        billingServiceGrpcClient.createBillingAccount(patient.getId().toString(), patient.getName(),patient.getEmail());


        return PatientMapper.toDTO(patient);
        // here we add data patient to database and convert to DTO type and return ;
        }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){

        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found "+id));
        if ( patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistException("a Patient email is already Exist "+ patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setEmail(patientRequestDTO.getEmail());

        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    public  void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }












}

