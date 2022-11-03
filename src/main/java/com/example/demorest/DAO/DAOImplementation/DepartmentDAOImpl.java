package com.example.demorest.DAO.DAOImplementation;

import com.example.demorest.Bean.Department;
import com.example.demorest.Bean.Employee;
import com.example.demorest.DAO.DepartmentDAO;

import com.example.demorest.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public boolean addDepartment(Department deptObj) {
        try(Session session = HibernateSessionUtil.getSession()){  // session created got access of hibernate session object
            Transaction transaction = session.beginTransaction();  // transaction initiated
            session.save(deptObj);                                 // using session object to save java object into MySQL
            transaction.commit();                                  // committing transaction
            return true;
        }
        catch (HibernateException exception) {
            // if Hibernate Exception occurs return false
            // for related exception we can maintain separate logger / Sysout messages for easy debugging
            System.out.println("Hibernate Exception");
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
        catch (Exception e){
            //generalized exception class for any IO / Arithmetic Exception
            System.out.print(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Department> getDepartmentList() {
        try (Session session = HibernateSessionUtil.getSession()){
            List<Department> departmentList = new ArrayList<>();
            for (final Object d : session.createQuery("from Department ").list()) {
                departmentList.add((Department) d);
            }
            return departmentList;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
    }
}