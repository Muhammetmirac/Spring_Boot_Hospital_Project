package com.runners.service;

import com.runners.domain.Appointment;
import com.runners.domain.Doctor;
import com.runners.dto.AppRequest;
import com.runners.dto.AppointmentDto;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppoinetmentService {

    private final AppointmentRepository appointmentRepository;

    private final PatientService patientService;

    private final DoctorService doctorService;

    public AppoinetmentService(AppointmentRepository appointmentRepository, PatientService patientService, DoctorService doctorService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }


    public void createAppointment(Appointment appointment) {

        boolean patientExists =  patientService.existPatientById(appointment.getPatientId());
        boolean doctorExists = doctorService.existDoctorById(appointment.getDoctorId());

        if(!patientExists ){
            throw new ResourceNotFoundException("Patient doesn't exist ! Please create new patient.");
        } else if (!doctorExists) {
            throw new ResourceNotFoundException("Doctor doesn't exist ! Please choose another doctor.");
        }else{
            appointment.setDoctor(doctorService.getDoctorById(appointment.getDoctorId()));
            appointment.setPatient(patientService.getByIdPatient(appointment.getPatientId()));
            appointmentRepository.save(appointment);
        }

    }

    public List<AppointmentDto> getAllAppointment() {
      List<Appointment> appointmentList =  appointmentRepository.findAll();
      List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for (Appointment app: appointmentList             ) {
            AppointmentDto appointmentDto = new AppointmentDto(app);
            appointmentDtoList.add(appointmentDto);
        }
              return    appointmentDtoList;
    }


    public AppointmentDto findAppDto(Long id) {
      Appointment appointment =  appointmentRepository.findById(id).orElseThrow(
              ()-> new ResourceNotFoundException("Appoinment not found by id : "+id));
      AppointmentDto appointmentDto = new AppointmentDto(appointment);
      return appointmentDto;
    }

    public void updateAppointment(Long id, AppRequest appRequest) {
      Doctor doctor = doctorService.getDoctorById(appRequest.getDoctorId());
      Appointment appointment = appointmentRepository.findById(id).orElseThrow(
              ()-> new ResourceNotFoundException(id+" id'li Appointment bulunamadı...")
      );

      appointment.setDate(appRequest.getDate());
      appointment.setHour(appRequest.getHour());
      appointment.setMinute(appRequest.getMinute());
      appointment.setNotes(appRequest.getNotes());

      appointment.setDoctor(doctor);

      appointmentRepository.save(appointment);
    }


    public void deleteAppointment(Long id) {
       if (appointmentRepository.existsById(id)){
           appointmentRepository.deleteById(id);
       }else throw new ResourceNotFoundException(id + " id'li Appointment bulunamadı...");
    }
}

