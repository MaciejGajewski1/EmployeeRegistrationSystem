package com.emplregsys.ers.service;

import com.emplregsys.ers.dao.EmployeeDao;
import com.emplregsys.ers.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        return employeeDao.createEmployee(employeeDto);
    }

    @Override
    public Employee setPersonalData(Long id, PersonalDataDto personalDataDto) {
        return employeeDao.setPersonalData(id, personalDataDto);
    }

    @Override
    public Employee setContactDetails(Long id, ContactDetailsDto contactDetailsDto) {
        return employeeDao.setContactDetails(id, contactDetailsDto);
    }

    @Override
    public Employee setImage(Long id, MultipartFile imageFile) throws IOException {
        return employeeDao.setImage(id, imageFile);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeDao.getEmployee(id);
    }

    @Override
    public Optional<PersonalData> getPersonalData(Long id) {
        return employeeDao.getPersonlData(id);
    }

    @Override
    public Optional<ContactDetails> getContactDetails(Long id) {
        return employeeDao.getContactDetails(id);
    }

    @Override
    public Optional<byte[]> getImage(Long id) {
        return employeeDao.getImage(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }
}
