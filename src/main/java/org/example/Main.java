package org.example;

import org.hibernate.Session;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {


    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = new Employee("Dimitri", "Luck", "f");
        Employee employee1 = new Employee("Jon", "Doe", "ddi");
        Employee employee2 = new Employee("WIll", "Zoe", "di");
        Employee employee3 = new Employee("Abe", "Low", "ddfi");
        dao.addEmployee(employee);
        dao.addEmployee(employee1);
        dao.addEmployee(employee2);
        dao.addEmployee(employee3);
//        dao.deleteEmployee(employee2);
        Optional<Employee> employees = dao.getEmployeeById(1);
        if(employees.isPresent()){
            System.out.println("Employee found "+ employees.get());
        }else {
            System.out.println("Employee not found");
        }

        dao.updateEmployeeById(1, new Employee("Frank", "Bigs","email"));
        List<Employee> emps = dao.getAllEmployees();
        List<Employee> emps1 = emps.stream().sorted(Comparator.comparing(Employee::getFirst_name)).toList();
        emps1.forEach(System.out::println);













    }
}