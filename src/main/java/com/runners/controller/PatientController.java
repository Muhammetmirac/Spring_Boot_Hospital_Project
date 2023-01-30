package com.runners.controller;

import com.runners.domain.Patient;
import com.runners.dto.PatientDto;
import com.runners.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @PostMapping
    public ResponseEntity<Map<String,String>> createPatient(@Valid @RequestBody Patient patient){
        patientService.createPatient(patient);
        Map<String,String> map= new HashMap<>();
        map.put("message", "Patient is created successfully");
        map.put("status", "true");
     return new ResponseEntity<>(map,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patientList = patientService.getAll();
        return ResponseEntity.ok(patientList);
    }

    @GetMapping("/query")
    public  ResponseEntity<PatientDto> getByIdPatientDto(@RequestParam Long id){
        PatientDto patientDto = patientService.getByIdPatient(id);
        return  ResponseEntity.ok(patientDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id){
        patientService.deleteByIdPatient(id);
        String message = "Patient is deleted successfully !";
        return ResponseEntity.ok(message);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id){
//        patientService.deleteByIdPatient(id);
//        String message = " Silme işlemi başarılı";
//
//        return ResponseEntity.ok(message);
//    }

    @PutMapping("/{id}")
    public  ResponseEntity<Map<String, String>> updatePatient(@PathVariable ("id") Long id,
                                                              @Valid @RequestBody PatientDto patientDto ){
        patientService.updatePatient(id,patientDto);

        Map<String,String> map = new HashMap<>();
        map.put("message", "Doctor is updated successfully");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

}
