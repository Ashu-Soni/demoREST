package com.example.demorest.DAO.DAOImplementation;

import com.example.demorest.Bean.Project;
import com.example.demorest.DAO.ProjectDAO;
import com.example.demorest.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
    @Override
    public boolean addProject(Project projectObj) {
        try(Session session = HibernateSessionUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.save(projectObj);
            t.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }


    @Override
    public List<Project> getProjectList() {
        try (Session session = HibernateSessionUtil.getSession()){
            List<Project> projectsList = new ArrayList<>();
            for (final Object p : session.createQuery("from Project ").list()) {
                projectsList.add((Project) p);
            }
            return projectsList;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean updateProjectName(int projectID, String updatedName) {

        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction t = session.beginTransaction();

            Query q = session.createQuery("from Project where projectID=:ID");
            q.setParameter("ID", 1);

            Project result = (Project)q.list().get(0);
            result.setProjectName(updatedName);
            session.update(result);
            t.commit();
            return true;

        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }
}