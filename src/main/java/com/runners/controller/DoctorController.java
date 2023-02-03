package com.runners.controller;

import com.runners.domain.Doctor;
import com.runners.dto.DoctorDTO;
import com.runners.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Map<String,String>> createDoctor(@Valid @RequestBody Doctor doctor){
        doctorService.createDoctor(doctor);
        Map<String,String> map= new HashMap<>();
        map.put("message", "Doctor is created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctor(){
      List<DoctorDTO> doctors =  doctorService.getAllDoctor();
        return ResponseEntity.ok(doctors);

    }

    @GetMapping("/{id}") // http://localhost:8080/v1/doctor/1
    public ResponseEntity<DoctorDTO> getDocDTOById(@PathVariable("id") Long id){

        DoctorDTO dto = doctorService.getByIdDTO(id);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable Long id){
        doctorService.deleteDoctorById(id);
        String message = "Doctor is deleted successfully";
        return ResponseEntity.ok(message);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<Map<String,String>> updateDoctor(@PathVariable Long id,
                                                            @Valid @RequestBody DoctorDTO doctorDTO){
    doctorService.updateDoctor(id, doctorDTO);

    Map<String,String> map = new HashMap<>();
    map.put("message", "Doctor is updated successfully");
    map.put("status", "true");

    return new ResponseEntity<>(map,HttpStatus.OK);

    }
}
