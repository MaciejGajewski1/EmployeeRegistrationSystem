package com.emplregsys.ers.controller;

import com.emplregsys.ers.model.*;
import com.emplregsys.ers.service.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<EntityModel<Employee>> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.createEmployee(employeeDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/employees/{id}")
                .buildAndExpand(employee.getId());
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(EntityModel.of(
                        employee,
                        linkTo(methodOn(EmployeeController.class).createEmployee(employeeDto)).withSelfRel()
                ));
    }

    @PutMapping("employees/{id}/personaldata")
    public ResponseEntity<Object> setPersonalData(@PathVariable Long id, @RequestBody PersonalDataDto personalDataDto) {
        try {
            employeeService.setPersonalData(id, personalDataDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("employees/{id}/contactdetails")
    public ResponseEntity<Object> setContactDetails(@PathVariable Long id, @RequestBody ContactDetailsDto contactDetailsDto) {
        try {
            employeeService.setContactDetails(id, contactDetailsDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/employees/{id}/image")
    public ResponseEntity<Object> setImage(@PathVariable Long id, @RequestPart MultipartFile imageFile) throws IOException {
        try {
            employeeService.setImage(id, imageFile);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getEmployee(id));
    }

    @GetMapping("employees/{id}/personal-data")
    public ResponseEntity<PersonalData> getPersonalData(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getPersonalData(id));
    }

    @GetMapping("employees/{id}/contact-details")
    public ResponseEntity<ContactDetails> getContactDetails(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getContactDetails(id));
    }

    @GetMapping(
            value = "employees/{id}/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getImage(id));
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
