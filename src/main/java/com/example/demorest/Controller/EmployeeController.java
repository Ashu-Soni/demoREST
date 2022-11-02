package com.example.demorest.Controller;

import com.example.demorest.Bean.Employee;
import com.example.demorest.DAO.DAOImplementation.EmployeeDAOImpl;
import com.example.demorest.DAO.EmployeeDAO;
import com.example.demorest.HelloApplication;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employee")
public class EmployeeController extends HelloApplication {
    private EmployeeDAOImpl employeeDAO;

    public EmployeeController(){
        employeeDAO = new EmployeeDAOImpl();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add_employee(Employee emp){
        System.out.println(emp);
        if(this.employeeDAO.addEmployee(emp)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(404).entity("Failure while adding employee").build();
    }

    @GET
    @Path("/get/{e_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response get_employee(@PathParam("e_id") int id){
        System.out.println(id);
        Employee emp = this.employeeDAO.getEmployeeByID(id);
        if(emp!=null){
            return Response.status(200).entity(emp).build();
        }

        return Response.status(400).entity("Failure in getting employee details").build();
    }
}
