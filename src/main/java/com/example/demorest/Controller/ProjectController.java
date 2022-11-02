package com.example.demorest.Controller;

import com.example.demorest.Bean.Project;
import com.example.demorest.DAO.DAOImplementation.ProjectDAOImpl;
import com.example.demorest.DAO.ProjectDAO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/project")
public class ProjectController {
    ProjectDAO pDAO = new ProjectDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_project(Project p){
        if(pDAO.addProject(p)){
            return Response.status(200).entity("Success").build();
        }
        return Response.status(404).entity("Failure while adding project").build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_projects(){
        List<Project> projects = pDAO.getProjectList();

        return Response.status(200).entity(projects).build();
    }

    @POST
    @Path("/update/{p_id}/{p_name}")
    public Response update_project(@PathParam("p_id") int pId, @PathParam("p_name") String pName){
        System.out.println(pId + " " + pName);
        if(pDAO.updateProjectName(pId, pName)){
            return Response.status(200).entity("Success").build();
        }

        return Response.status(404).entity("Failure while updating project name").build();
    }
}
