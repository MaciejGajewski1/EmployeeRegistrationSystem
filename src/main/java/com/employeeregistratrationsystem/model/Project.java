package com.employeeregistratrationsystem.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private String name;
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.getProjects().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        final Project project = (Project) o;
        return uuid.equals(project.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
