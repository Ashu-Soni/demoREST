package com.example.demorest.Controller;

import com.example.demorest.Bean.Department;
import com.example.demorest.Bean.Employee;
import com.example.demorest.DAO.DAOImplementation.DepartmentDAOImpl;
import com.example.demorest.DAO.DepartmentDAO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/department")
public class DepartmentController {
    DepartmentDAO deptDAO = new DepartmentDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_department(Department dept){
        System.out.printf(String.valueOf(dept));
        if(deptDAO.addDepartment(dept)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while adding department").build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_departments(){
        List<Department> depts = deptDAO.getDepartmentList();
        System.out.printf("Hello world");
        return Response.status(200).entity(depts).build();
    }

//    @GET
//    @Path("/get_employees/{dept_id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response get_department(@PathParam("dept_id") int d_id){
//        System.out.println(d_id);
//
//        List<Employee> employees = deptDAO.employeeListInDepartment(d_id);
//
//        return Response.status(200).entity(employees).build();
//    }
}
