package org.example;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class EmployeeDao {

    public void deleteEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public Optional<Employee> getEmployeeById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Employee.class, id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
//    }
    }

    public void updateEmployeeById(long id, Employee employee){


        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Optional<Employee> optionalEmployee = getEmployeeById(id);
            if (optionalEmployee.isPresent()){
                optionalEmployee.get().setFirst_name(employee.getFirst_name());
                optionalEmployee.get().setLast_name(employee.getLast_name());
                optionalEmployee.get().setEmail(employee.getEmail());

                session.getTransaction().begin();
                session.merge(optionalEmployee.get());
                session.getTransaction().commit();

                }else {
                System.out.println("Not found "+ id);
            }


            }catch (Exception e){
            e.printStackTrace();
        }

        }
    }

