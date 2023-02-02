package com.emplregsys.ers.dao;

import com.emplregsys.ers.exceptions.DepartmentNotFoundException;
import com.emplregsys.ers.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Transactional
public class EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeDto.toEmployee();
        em.persist(employee);
        return employee;
    }

    public Employee setPersonalData(Long id, PersonalDataDto personalDataDto) {
        Employee employee = em.find(Employee.class, id);
        employee.addPersonalData(personalDataDto.toPersonalData());
        em.persist(employee);
        return employee;
    }

    public Employee setContactDetails(Long id, ContactDetailsDto contactDetailsDto) {
        Employee employee = em.find(Employee.class, id);
        employee.addContactDetails(contactDetailsDto.toContactDetails());
        em.persist(employee);
        return employee;
    }

    public Employee setImage(Long id, MultipartFile imageFile) throws IOException {
        Employee employee = em.find(Employee.class, id);
        if (employee == null) {
            throw new NoSuchElementException("Employee id: " + id + " does not exists.");
        }
        Image savedImage = em.find(Image.class, id);
        if (savedImage != null) {
            savedImage.setImageData(imageFile.getBytes());
            em.persist(savedImage);
        } else {
            Image image = new Image();
            image.setImageData(imageFile.getBytes());
            employee.addImage(image);
            em.persist(image);
            em.persist(employee);
        }
        return employee;
    }

}
