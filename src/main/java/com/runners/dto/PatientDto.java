package com.runners.dto;

import com.runners.domain.Patient;
import com.runners.domain.enums.City;
import com.runners.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
@Setter
public class PatientDto {
    @NotBlank(message = "First name cannot be empty or blank !")
    @Size(min=2,max = 30,message = "First name '${validatedValue}' must be at least min and max characters")
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    private Integer dateOfBirth;

    @Enumerated(EnumType.STRING)
    private City city;

    private String address;

    private boolean healthInsurance;


    public PatientDto(Patient patient) {
        this.firstName = patient.getName();
        this.gender = patient.getGender();
        this.dateOfBirth = patient.getDateOfBirth();
        this.city = patient.getCity();
        this.address = patient.getAddress();
        this.healthInsurance = patient.isHealthInsurance();
    }
}
