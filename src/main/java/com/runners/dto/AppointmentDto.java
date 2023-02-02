package com.runners.dto;

import com.runners.domain.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    @NotNull
    @NotBlank
    private String date;

    @NotNull

    private Integer hour;


    @NotNull
    private Integer minute;


    private String notes;

    private String patientName;

    private String doctorName;

    public AppointmentDto (Appointment appointment) {
        this.doctorName= appointment.getDoctor().getName();
        this.patientName= appointment.getPatient().getName();
        this.date= appointment.getDate();
        this.hour = appointment.getHour();
        this.minute = appointment.getMinute();
        this.notes = appointment.getNotes();
    }

}


