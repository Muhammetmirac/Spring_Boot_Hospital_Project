package com.runners.repository;

import com.runners.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    boolean existsByTckn(String tckn);




}
