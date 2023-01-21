package com.employeeregistratrationsystem.service;

import com.employeeregistratrationsystem.exceptions.DepartmentNotFoundException;
import com.employeeregistratrationsystem.model.Department;
import com.employeeregistratrationsystem.model.DepartmentDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

@Service
class DepartmentServiceImpl implements DepartmentService {

    @PersistenceContext
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private EntityManager em = entityManagerFactory.createEntityManager();


    @Override
    public Department createDepartment(DepartmentDto departmentDto) {
        em.getTransaction().begin();

        Department department = new Department();
        department.setName(departmentDto.getName());
        em.persist(department);

        em.getTransaction().commit();
        return department;
    }

    @Override
    public Optional<Department> getDepartment(Long id) {
        em.getTransaction().begin();

        Department department = em.find(Department.class, id);

        em.getTransaction().commit();

        Optional<Department> optional = Optional.ofNullable(department);

        return optional;
    }

    @Override
    public List<Department> getDepartments() {
        em.getTransaction().begin();
        List<Department> departments = em.createQuery("select d from Department d", Department.class).getResultList();
        em.getTransaction().commit();
        return departments;
    }

    @Override
    public void deleteDepartment(Long id) {
        em.getTransaction().begin();

        Department department = em.find(Department.class, id);
        if (department == null) {
            throw new DepartmentNotFoundException(id);
        }
        em.remove(department);

        em.getTransaction().commit();
    }
}



