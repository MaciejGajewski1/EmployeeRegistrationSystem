package com.employeeregistratrationsystem.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(final long id) {
        super("Department for id" + id + " not found");
    }
}
