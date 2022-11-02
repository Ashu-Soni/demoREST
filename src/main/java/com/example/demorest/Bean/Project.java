package com.example.demorest.Bean;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="project_id")
    private int projectID;

    @Column(name= "project_name",  nullable = false)
    private String projectName;

    @ManyToMany (fetch = FetchType.EAGER, targetEntity = Employee.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "works_on",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "emp_id") })
    private List<Employee> employeesList;


    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Project(){}

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    @Override
    public String toString() {
        return "\nProject{" +
                "projectID=" + projectID +
                ", projectName='" + projectName + '\'' +
                ", \nemployeesList=" + employeesList +
                '}';
    }
}
