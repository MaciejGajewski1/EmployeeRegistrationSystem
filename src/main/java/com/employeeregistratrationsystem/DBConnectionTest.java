package com.employeeregistratrationsystem;

import com.employeeregistratrationsystem.model.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

class DBConnectionTest {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        Employee employee = new Employee();
        employee.setProffesion("Programmer");
        employee.setEmploymentDate(LocalDateTime.now());
        employee.setEmploymentType(EmploymentType.EMPLOYMENT_CONTRACT);
        employee.setSalary(new BigDecimal("5500.00"));

        Image image = new Image();
        image.setEmployee(employee);

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setCity("Poznan");
        contactDetails.setEmail("mgajewski101@gmail.com");
        contactDetails.setStreet("Osiedle Lecha 60 / 9");
        contactDetails.setPostalCode("61-295");
        contactDetails.setPhoneNumber("884315297");
        contactDetails.setEmployee(employee);

        PersonalData personalData = new PersonalData();
        personalData.setBirthday(LocalDate.of(1994, 12, 01));
        personalData.setName("Maciej");
        personalData.setSurename("Gajewski");
        personalData.setPESEL(new byte[]{9, 4, 1, 2, 0, 1, 0, 6, 5, 1, 9});
        personalData.setEmployee(employee);

        Project project = new Project();
        project.setName("EmployeeRegistrationSystem");
        employee.addProject(project);

        Department department = new Department();
        department.setName("Dzial IT");
        department.setManager(employee);
        department.addEmployee(employee);

        employee.setContactDetails(contactDetails);
        employee.setImage(image);
        employee.setPersonalData(personalData);


        em.persist(department);

        em.getTransaction().commit();
        em.close();
    }
}
