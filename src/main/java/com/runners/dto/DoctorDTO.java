package com.runners.dto;


import com.runners.domain.Doctor;
import com.runners.domain.enums.Department;
import com.runners.domain.enums.Prefix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDTO {


    @Size(min = 2, max = 30, message = "First name '${validatedValue}' must be between {min} and {max} long")
    @NotBlank(message = "First name cannot be empty or blank !")
    private String firstName;



    private Prefix prefix;


    private String tcNo;


    private Department department;


    private Integer dateOfGraduate;


    private Integer dateOfStart;

    public DoctorDTO(Doctor doctor){

        this.firstName = doctor.getName();
        this.prefix = doctor.getPrefix();
        this.tcNo = doctor.getTckn();
        this.department = doctor.getDepartment();
        this.dateOfGraduate = doctor.getDateOfGraduate();
        this.dateOfStart = doctor.getDateOFStart();

    }




}