package com.runners.domain;

import com.runners.domain.enums.City;
import com.runners.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "First name cannot be empty or blank !")
    @Size(min=2,max = 30,message = "First name '${validatedValue}' must be at least min and max characters")
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer dateOfBirth;

    @Enumerated(EnumType.STRING)
    private City city;

    private String address;

    private boolean healthInsurance;


    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList = new ArrayList<>();





}