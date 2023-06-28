package org.kainos.ea.api;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.FailedToGetOrdersException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public List<IPayable> getEmployees() throws FailedToGetOrdersException {
        List<Employee> employeeList = null;
        Employee emp = new Employee(1,"Conor", 19000);
        SalesEmployee sales = new SalesEmployee(1, "Conor", 19000, 1000, 0.01f);
        Contractor contractor = new Contractor("Conor", 1000,10);
        List<IPayable> employees = new ArrayList<>();
        employees.add(emp);
        employees.add(sales);
        employees.add(contractor);

        for (IPayable e: employees)
        {
            System.out.println(e.calcPay());
        }
        return employees;


    }
}
