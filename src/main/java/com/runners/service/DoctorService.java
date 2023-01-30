package com.runners.service;

import com.runners.domain.Doctor;
import com.runners.dto.DoctorDTO;
import com.runners.exception.ConflictException;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void createDoctor(Doctor doctor) {
        if (doctorRepository.existsByTckn(doctor.getTckn())){
            throw new ConflictException("TcNo already exists !");
        }
        doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();

    }

    public DoctorDTO getByIdDTO(Long id) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Doctor not found by id : "+id));
        DoctorDTO doctorDto = new DoctorDTO(doctor);

        return doctorDto;

    }

    public void deleteDoctorById(Long id) {
       if (doctorRepository.existsById(id)){
           doctorRepository.deleteById(id);
       }
        else new ResourceNotFoundException(id + " id nolu Doktor bulunamadı");
    }

    public void updateDoctor(Long id, DoctorDTO doctorDTO) {

        boolean existTc = doctorRepository.existsByTckn(doctorDTO.getTcNo());

        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found by id : " + id));

        if(existTc && ! doctorDTO.getTcNo().equals(doctor.getTckn())){
            throw new ConflictException("Tcno already exists !");
        }
        doctor.setName(doctorDTO.getFirstName());
        doctor.setTckn(doctorDTO.getTcNo());
        doctor.setDepartment(doctorDTO.getDepartment());
        doctor.setPrefix(doctorDTO.getPrefix());
        doctor.setDateOfGraduate(doctorDTO.getDateOfGraduate());
        doctor.setDateOFStart(doctorDTO.getDateOfStart());

        doctorRepository.save(doctor);

    }
}
