package com.runners.service;

import com.runners.domain.Patient;
import com.runners.dto.PatientDto;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public PatientDto getByIdPatientDto(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id + " id nolu Patient(hasta) bulunamadı... "));
        PatientDto patientDto = new PatientDto(patient);
        return patientDto;
    }


    public void deleteByIdPatient(Long id) {
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Patient not found by id : "+id);
    }
//    public void deleteByIdPatient(Long id) {
//
//        if (patientRepository.existsById(id)) {
//            patientRepository.deleteById(id);
//        } else new ResourceNotFoundException(id + " id nolu Hasta bulunamadı");
//    }

    public void updatePatient(Long id, PatientDto patientDto) {
        if (!patientRepository.existsById(id)) {
            new ResourceNotFoundException(id + " id nolu Hasta bulunamadı");
        }
        Patient patient = patientRepository.getById(id);

        patient.setName(patientDto.getFirstName());
        patient.setGender(patientDto.getGender());
        patient.setCity(patientDto.getCity());
        patient.setAddress(patientDto.getAddress());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setHealthInsurance(patientDto.isHealthInsurance());

        patientRepository.save(patient);

    }

    public boolean existPatientById(Long id){
      boolean exist = patientRepository.existsById(id);
        return exist;

    }

    public Patient getByIdPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id + " id nolu Patient(hasta) bulunamadı... "));

        return patient;
    }

}
