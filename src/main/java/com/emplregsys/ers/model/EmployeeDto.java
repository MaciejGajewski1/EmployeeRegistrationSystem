package com.emplregsys.ers.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String proffesion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate employmentDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal salary;
    private EmploymentType employmentType;

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setProffesion(this.proffesion);
        employee.setEmploymentDate(this.employmentDate);
        employee.setSalary(this.getSalary());
        employee.setEmploymentType(this.getEmploymentType());
        return employee;
    }
}
