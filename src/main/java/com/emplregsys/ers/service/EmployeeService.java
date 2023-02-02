package com.emplregsys.ers.service;

import com.emplregsys.ers.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);
    Employee setPersonalData(Long id, PersonalDataDto personalDataDto);
    Employee setContactDetails(Long id, ContactDetailsDto contactDetailsDto);
    Employee setImage(Long id, MultipartFile multipartFile) throws IOException;
}
