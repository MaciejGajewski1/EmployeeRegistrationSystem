package com.emplregsys.ers.dao;

import com.emplregsys.ers.exceptions.DepartmentNotFoundException;
import com.emplregsys.ers.model.Department;
import com.emplregsys.ers.model.DepartmentDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class DepartmentDao {

    @PersistenceContext
    private EntityManager em;

    public Department createDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        em.persist(department);
        return department;
    }

    public Optional<Department> getDepartment(Long id) {
        Department department = em.find(Department.class, id);
        return Optional.ofNullable(department);
    }

    public List<Department> getDepartments() {
        List<Department> departments = em.createQuery(
                "select d from Department d",
                        Department.class)
                        .getResultList();
        return departments;
    }

    public void deleteDepartment(Long id) {
        Department department = em.find(Department.class, id);
        if (department == null) {
            throw new DepartmentNotFoundException(id);
        }
        em.remove(department);
    }

}
