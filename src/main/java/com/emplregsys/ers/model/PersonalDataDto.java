package com.emplregsys.ers.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataDto {
    private String name;
    private String surename;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    private Long pesel;

    public PersonalData toPersonalData() {
        PersonalData personalData = new PersonalData();
        personalData.setName(this.name);
        personalData.setSurename(this.surename);
        personalData.setBirthday(this.birthDay);
        personalData.setPesel(this.pesel);
        return personalData;
    }
}
