package org.kainos.ea.cli;

public  class Employee implements IPayable {
    private int employeeId;
    private String name;
    private double salary;
    private  double monthlysales;
    private float commisionRate;
    public int getEmployeeId() {
        return employeeId;
    }

    public double getMonthlysales() {
        return monthlysales;
    }

    public void setMonthlysales(double monthlysales) {
        this.monthlysales = monthlysales;
    }

    public float getCommisionRate() {
        return commisionRate;
    }

    public void setCommisionRate(float commisionRate) {
        this.commisionRate = commisionRate;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(int employeeId, String name, double salary) {
       setEmployeeId(employeeId);
       setName(name);
       setSalary(salary);
    }
    public double calcPay() {
            return getSalary() / 12;
    }
}
