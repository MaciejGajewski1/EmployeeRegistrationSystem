package com.emplregsys.ers.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;


@Entity
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surename;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    @Column(name = "pesel")
    private byte PESEL[];
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalData)) return false;
        final PersonalData that = (PersonalData) o;
        return name.equals(that.name) && surename.equals(that.surename) && Arrays.equals(PESEL, that.PESEL);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthday(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public byte[] getPESEL() {
        return PESEL;
    }

    public void setPESEL(byte[] PESEL) {
        this.PESEL = PESEL;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
