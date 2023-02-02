package com.runners.controller;

import com.runners.domain.Appointment;
import com.runners.dto.AppRequest;
import com.runners.dto.AppointmentDto;
import com.runners.service.AppoinetmentService;
import com.runners.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/appointment")
public class AppointmentController {
    private final AppoinetmentService appoinetmentService;




    public AppointmentController(AppoinetmentService appoinetmentService) {
        this.appoinetmentService = appoinetmentService;

    }

    @PostMapping
    public ResponseEntity< String> createAppointment(@Valid @RequestBody Appointment appointment){
        appoinetmentService.createAppointment(appointment);
        String message = "Hasta Randevu Kaydı Başarılı";
        return ResponseEntity.ok(message);

    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointmentDto(){
      List<AppointmentDto> list =  appoinetmentService.getAllAppointment();
      return   ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppointmentDto> getAppDtoById(@PathVariable("id") Long id){

        AppointmentDto appDto = appoinetmentService.findAppDto(id);
        return ResponseEntity.ok(appDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id,
                                                            @Valid @RequestBody AppRequest appRequest){
      appoinetmentService.updateAppointment(id,appRequest);

      String message = " Appoinetment kaydı başarılı...";
        return  ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        appoinetmentService.deleteAppointment(id);
        String message = "Silme işlemi başarılı...";
        return ResponseEntity.ok(message);
    }

}
