package com.runners.dto;


import com.runners.domain.Appointment;
import com.runners.domain.enums.City;
import com.runners.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppDocDto {


    private String date;

    private Integer hour;

    private Integer minute;

    private String firstName;


    private Gender gender;


    private Integer dateOfBirth;


    private boolean healthInsurance;

    public AppDocDto(Appointment appointment) {

        this.date =appointment.getDate();
        this.hour = appointment.getHour();
        this.minute= appointment.getMinute();
        this.dateOfBirth=appointment.getPatient().getDateOfBirth();
        this.gender = appointment.getPatient().getGender();
        this.firstName = appointment.getPatient().getName();
        this.healthInsurance= appointment.getPatient().isHealthInsurance();
    }
}
