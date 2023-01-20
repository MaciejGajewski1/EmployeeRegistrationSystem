package com.employeeregistratrationsystem.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String proffesion;
    @Column(name = "employment_date")
    private LocalDateTime employmentDate;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType EmploymentType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private ContactDetails contactDetails;
    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private PersonalData personalData;
    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Image image;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project) {
        projects.add(project);
        project.getEmployees().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        final Employee employee = (Employee) o;
        return personalData.equals(employee.personalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    public LocalDateTime getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public com.employeeregistratrationsystem.model.EmploymentType getEmploymentType() {
        return EmploymentType;
    }

    public void setEmploymentType(com.employeeregistratrationsystem.model.EmploymentType employmentType) {
        EmploymentType = employmentType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
