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
        if (employee == null) {
            throw new NoSuchElementException("Employee id: " + id + " does not exists.");
        }
        PersonalData savedPersonalData = em.find(PersonalData.class, id);
        if (savedPersonalData != null) {
            savedPersonalData.setName(personalDataDto.getName());
            savedPersonalData.setSurename(personalDataDto.getSurename());
            savedPersonalData.setBirthday(personalDataDto.getBirthDay());
            savedPersonalData.setPesel(personalDataDto.getPesel());
            em.persist(savedPersonalData);
        } else {
            PersonalData personalData = personalDataDto.toPersonalData();
            employee.addPersonalData(personalData);
            em.persist(personalData);
            em.persist(employee);
        }
        return employee;
    }

    public Employee setContactDetails(Long id, ContactDetailsDto contactDetailsDto) {
        Employee employee = em.find(Employee.class, id);
        if (employee == null) {
            throw new NoSuchElementException("Employee id: " + id + " does not exists.");
        }
        ContactDetails savedContactDetails = em.find(ContactDetails.class, id);
        if (savedContactDetails != null) {
            savedContactDetails.setPhoneNumber(contactDetailsDto.getPhoneNumber());
            savedContactDetails.setEmail(contactDetailsDto.getEmail());
            savedContactDetails.setStreet(contactDetailsDto.getStreet());
            savedContactDetails.setPostalCode(contactDetailsDto.getPostalCode());
            savedContactDetails.setCity(contactDetailsDto.getCity());
            em.persist(savedContactDetails);
        } else {
            ContactDetails contactDetails = contactDetailsDto.toContactDetails();
            employee.addContactDetails(contactDetails);
            em.persist(contactDetails);
            em.persist(employee);
        }
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

    public Optional<Employee> getEmployee(Long id) {
        Employee employee = em.find(Employee.class, id);
        return Optional.ofNullable(employee);
    }

    public Optional<PersonalData> getPersonlData(Long id) {
        PersonalData personalData = em.find(PersonalData.class, id);
        return Optional.ofNullable(personalData);
    }

    public Optional<ContactDetails> getContactDetails(Long id) {
        ContactDetails contactDetails = em.find(ContactDetails.class, id);
        return Optional.ofNullable(contactDetails);
    }

    public Optional<byte[]> getImage(Long id) {
        Image image = em.find(Image.class, id);
        byte[] imageData = image.getImageData();
        return Optional.ofNullable(imageData);
    }

    public void deleteEmployee(Long id) {
        Employee employee = em.find(Employee.class, id);
        if (employee == null) {
            throw new NoSuchElementException("Employee id: " + id + " does not exists.");
        }
        em.remove(employee);
    }
}
