package com.emplregsys.ers.service;


import com.emplregsys.ers.dao.DepartmentDao;
import com.emplregsys.ers.model.Department;
import com.emplregsys.ers.model.DepartmentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public Department createDepartment(DepartmentDto departmentDto) {
        return departmentDao.createDepartment(departmentDto);
    }

    @Override
    public Optional<Department> getDepartment(Long id) {
        return departmentDao.getDepartment(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentDao.deleteDepartment(id);
    }
}
