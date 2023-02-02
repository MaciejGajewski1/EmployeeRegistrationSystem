package com.emplregsys.ers.controller;

import com.emplregsys.ers.model.Employee;
import com.emplregsys.ers.model.EmployeeDto;
import com.emplregsys.ers.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/employees/image/{id}")
    public ResponseEntity<Object> setImage(@PathVariable Long id, @RequestPart MultipartFile imageFile) throws IOException {
        try {
            Employee employee = employeeService.setImage(id, imageFile);
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
