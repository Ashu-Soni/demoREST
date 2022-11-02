package com.example.demorest.DAO.DAOImplementation;

import com.example.demorest.Bean.Employee;
import com.example.demorest.DAO.EmployeeDAO;
import com.example.demorest.Util.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean addEmployee(Employee empObj) {
        try(Session session = HibernateSessionUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.save(empObj);
            t.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Employee getEmployeeByID(int empID) {
        try (Session session = HibernateSessionUtil.getSession()) {
            return session.get(Employee.class, empID);
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }


    @Override
    public List<Employee> getEmployeeSalaryInRange(long lowerBound, long upperBound) {
        try(Session session = HibernateSessionUtil.getSession()){

            List<Employee> employeeList = new ArrayList<>();
            Query query = session.createQuery("from Employee where salary between :lowerSalary and :upperSalary");
            query.setParameter("lowerSalary", lowerBound);
            query.setParameter("upperSalary", upperBound);
            for (final Object fetch : query.list()) {
                employeeList.add((Employee) fetch);
            }
            return employeeList;
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }

    }

    @Override
    public boolean deleteEmployee(int empID) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction t = session.beginTransaction();
            //first fetch object and then delete it
            Employee empObj= session.get(Employee.class, empID);
            session.delete(empObj);
            t.commit();
            System.out.println( "Employee Deleted having employee ID - "+ empID);
            return true;
        }
        catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Double avgSalary() {
        try (Session session = HibernateSessionUtil.getSession()) {
            Query query = session.createQuery("select avg(salary) from Employee");

            double avgSalary = (Double) query.uniqueResult();
            return avgSalary;
        }
        catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
            return 0.0;
        }

    }


}