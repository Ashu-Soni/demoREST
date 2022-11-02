package com.example.demorest.Bean;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name= "department")
public class Department {
    @Id
    @Column(name ="dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentID;

    @Column(name="dept_name",nullable = false,unique = true)
    private String departmentName;

    @Column(name="dept_address")
    private String deptAddress;

    @OneToMany(mappedBy = "empDepartment" , fetch = FetchType.EAGER)    // name of class member variable in Employee class; it will be mapped with that variable
    private List<Employee> employeesList;

    public Department() {
    }

    public Department(int departmentID, String departmentName, String deptAddress, List<Employee> employeesList) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.deptAddress = deptAddress;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + departmentID +
                ", departmentName='" + departmentName + '\'' +
                ", deptAddress='" + deptAddress + '\'' +
                '}';
    }
}
