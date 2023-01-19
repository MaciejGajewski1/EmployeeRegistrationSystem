package com.employeeregistratrationsystem;

import com.employeeregistratrationsystem.model.Employee;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

class DBConnectionTest {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee();
        employee.setName("Jacek");
        employee.setSurename("Placek");
        employee.setAge(22);
        employee.setEmail("jacekplacek127@gmail.com");
        employee.setPhonenumber(123876345);
        employee.setProffesion("Programmer");
        employee.setEmploymentDate(LocalDateTime.now());

        em.persist(employee);

        em.getTransaction().commit();
        em.close();
    }
}
