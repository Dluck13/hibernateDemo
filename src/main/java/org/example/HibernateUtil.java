package org.example;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class).buildSessionFactory();

        } catch (HibernateException e) {
            System.err.println("Session factory failed "+e);
            throw new ExceptionInInitializerError(e);

        }

    }

    public static  SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
