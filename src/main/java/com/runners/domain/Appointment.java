package com.runners.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_patient")
    private Patient patient;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_doctor")

    private Doctor doctor;
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


}
