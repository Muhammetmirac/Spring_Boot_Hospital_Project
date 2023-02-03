package com.runners.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runners.domain.enums.Department;
import com.runners.domain.enums.Prefix;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 5)
    private String tckn;

    @Size(min = 2, max = 30, message = "First Name '${validatedValue}' must be between {min} and {max} long")
    @NotBlank(message = "First name cannot be empty or Blank ! ")
    private String name;

    @Enumerated(EnumType.STRING)
    private Prefix prefix;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    private Integer dateOfGraduate;

    private  Integer dateOFStart;


    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList = new ArrayList<>();







}
