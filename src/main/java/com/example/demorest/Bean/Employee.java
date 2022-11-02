package com.example.demorest.Bean;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "emaployee")
public class Employee {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID;

    @Column(name = "emp_name", nullable = false)
    private String employeeName;

    @Column(name= "emp_gender")
    private String employeeGender;

    @Column(name ="emp_salary")
    private long salary;

    @ManyToOne
    @JoinColumn(name="employee_dept_id")
    private Department empDepartment;

    @ManyToMany(mappedBy = "employeesList")
    private List<Project> projectList;

    public Employee(){

    }

    public Employee(int employeeID, String employeeName, String employeeGender, long salary, Department department) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.salary = salary;
        this.empDepartment = department;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return empDepartment;
    }

    public void setDepartment(Department department) {
        this.empDepartment = department;
    }

    public Department getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(Department empDepartment) {
        this.empDepartment = empDepartment;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    // to string to print object as a string otherwise it returns object Hashcode
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", employeeGender='" + employeeGender + '\'' +
                ", salary=" + salary +
                ", empDepartment=" + empDepartment +
                '}';
    }
}
