package com.example.demorest.DAO;

import com.example.demorest.Bean.Project;

import java.util.List;

public interface ProjectDAO {
    boolean addProject(Project projectObj);
    List<Project> getProjectList();
    boolean updateProjectName(int projectID,  String updatedName);
}