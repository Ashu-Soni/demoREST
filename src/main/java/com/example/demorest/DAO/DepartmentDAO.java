package com.example.demorest.DAO;

import com.example.demorest.Bean.Department;
import com.example.demorest.Bean.Employee;

import java.util.List;

public interface DepartmentDAO {
    boolean addDepartment(Department deptObj);
    List<Department> getDepartmentList();
    List<Employee> employeeListInDepartment(int deptID);
}