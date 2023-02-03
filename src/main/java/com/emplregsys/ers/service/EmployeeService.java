package com.emplregsys.ers.service;

import com.emplregsys.ers.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);
    Employee setPersonalData(Long id, PersonalDataDto personalDataDto);
    Employee setContactDetails(Long id, ContactDetailsDto contactDetailsDto);
    Employee setImage(Long id, MultipartFile multipartFile) throws IOException;
    Optional<Employee> getEmployee(Long id);
    Optional<PersonalData> getPersonalData(Long id);
    Optional<ContactDetails> getContactDetails(Long id);
    Optional<byte[]> getImage(Long id);
    void deleteEmployee(Long id);
}
