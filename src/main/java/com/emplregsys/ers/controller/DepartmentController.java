package com.emplregsys.ers.controller;

import com.emplregsys.ers.exceptions.DepartmentNotFoundException;
import com.emplregsys.ers.model.Department;
import com.emplregsys.ers.model.DepartmentDto;
import com.emplregsys.ers.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<EntityModel<Department>> createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.createDepartment(departmentDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/departments/{id}")
                .buildAndExpand(department.getId());
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(EntityModel.of(
                        department,
                        linkTo(methodOn(DepartmentController.class).createDepartment(departmentDto)).withSelfRel()
                ));
    }

    @GetMapping("/departments")
    public CollectionModel<EntityModel<Department>> getDepartments() {
        List<EntityModel<Department>> departments = departmentService.getDepartments().stream()
                .map(department -> EntityModel.of(
                        department,
                        linkTo(methodOn(DepartmentController.class).getDepartment(department.getId())).withSelfRel(),
                        linkTo(methodOn(DepartmentController.class).getDepartments()).withRel("departments")
                )).collect(Collectors.toList());
        return CollectionModel.of(
                departments,
                linkTo(methodOn(DepartmentController.class).getDepartments()).withSelfRel()
        );
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return ResponseEntity.of(departmentService.getDepartment(id));
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
