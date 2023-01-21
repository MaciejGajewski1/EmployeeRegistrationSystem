package com.employeeregistratrationsystem.service;

import com.employeeregistratrationsystem.model.Department;
import com.employeeregistratrationsystem.model.DepartmentDto;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department createDepartment(DepartmentDto departmentDto);
    Optional<Department> getDepartment(Long id);
    List<Department> getDepartments();
    void deleteDepartment(Long id);

}
