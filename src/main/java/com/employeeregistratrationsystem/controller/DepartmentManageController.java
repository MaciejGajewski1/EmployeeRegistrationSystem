package com.employeeregistratrationsystem.controller;

import com.employeeregistratrationsystem.exceptions.DepartmentNotFoundException;
import com.employeeregistratrationsystem.model.Department;
import com.employeeregistratrationsystem.model.DepartmentDto;
import com.employeeregistratrationsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
class DepartmentManageController {

    private final DepartmentService departmentService;

    @PostMapping("/departments")
    public ResponseEntity<EntityModel<Department>> createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.createDepartment(departmentDto);
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/department/{id}")
                .buildAndExpand(department.getId());
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(EntityModel.of(
                        department,
                        linkTo(methodOn(DepartmentManageController.class).createDepartment(departmentDto)).withSelfRel()
                ));
    }

    @GetMapping("/departments")
    public CollectionModel<EntityModel<Department>> getDepartments() {
        List<EntityModel<Department>> departments = departmentService.getDepartments().stream()
                .map(department -> EntityModel.of(
                        department,
                        linkTo(methodOn(DepartmentManageController.class).getDepartment(department.getId())).withSelfRel(),
                        linkTo(methodOn(DepartmentManageController.class).getDepartments()).withRel("departments")
                )).collect(Collectors.toList());
        return CollectionModel.of(
                departments,
                linkTo(methodOn(DepartmentManageController.class).getDepartments()).withSelfRel()
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
        return ResponseEntity.noContent().build();
    }
}
