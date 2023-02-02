package com.emplregsys.ers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum EmploymentType {
    EMPLOYMENT_CONTRACT("EC"),
    COMMISSION_CONTRACT("CC"),
    SPECIFIC_TASK_CONTRACT("STC"),
    INTERNSHIP_CONTRACT("IC"),
    B2B("B2B");
    private String employmentTypeCode;
    private EmploymentType(String employmentTypeCode) {
        this.employmentTypeCode = employmentTypeCode;
    }
    @JsonCreator
    public static EmploymentType getEmploymentTypeFromCode(String value) {

        for (EmploymentType et : EmploymentType.values()) {

            if (et.getEmploymentTypeCode().equals(value)) {
                return et;
            }
        }

        return null;
    }
}
