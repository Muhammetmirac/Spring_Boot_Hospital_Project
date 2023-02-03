package com.runners.domain;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;

    private Long patientId;

    @NotBlank
    @NotNull
    private String date;
   // @NotBlank
    @NotNull
    private Integer hour;
   // @NotBlank
    @NotNull
    private Integer minute;
    private String notes;


    @ManyToOne
    @JoinColumn(name = "appointment_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointment_doctor")
    private Doctor doctor;

}
