package com.example.demorest.Controller;

import com.example.demorest.Bean.Department;
import com.example.demorest.Bean.Employee;
import com.example.demorest.DAO.DAOImplementation.EmployeeDAOImpl;
import com.example.demorest.DAO.EmployeeDAO;
import com.example.demorest.HelloApplication;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employee")
public class EmployeeController extends HelloApplication {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add_employee(Employee emp){
        if(this.employeeDAO.addEmployee(emp)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(404).entity("Failure while adding employee").build();
    }

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_employee(){
        List<Employee> emps = this.employeeDAO.getEmployee();

        return Response.status(200).entity(emps).build();
    }

    @GET
    @Path("/get/{e_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response get_employee(@PathParam("e_id") int id){
        System.out.println(id);

        Employee emp = this.employeeDAO.getEmployeeByID(id);
        emp.setDepartment(emp.getDepartment());
        System.out.println(emp);

        return Response.status(200).entity(emp).build();
    }

    @GET
    @Path("/employee_in_range/{lower}/{upper}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response employee_in_range(@PathParam("lower") long lower, @PathParam("upper") long upper){
        List<Employee> emps = employeeDAO.getEmployeeSalaryInRange(lower, upper);

        return Response.status(200).entity(emps).build();
    }

    @DELETE
    @Path("/delete/{e_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("e_id") int id){
        if(employeeDAO.deleteEmployee(id)){
            return Response.status(200).entity("Success").build();
        }
        return Response.status(400).entity("Failure while deleting employee").build();
    }

    @GET
    @Path("/avg_salary")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get_avg_salary(){
        Double avg = employeeDAO.avgSalary();

        return Response.status(200).entity(avg).build();
    }
}
