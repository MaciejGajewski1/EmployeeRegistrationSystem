package com.emplregsys.ers.service;

import com.emplregsys.ers.model.Department;
import com.emplregsys.ers.model.DepartmentDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department createDepartment(DepartmentDto departmentDto);
    Optional<Department> getDepartment(Long id);
    List<Department> getDepartments();
    void deleteDepartment(Long id);

}
