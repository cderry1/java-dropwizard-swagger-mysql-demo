package org.kainos.ea.cli;

public class SalesEmployee  extends  Employee{

    private double monthlysales;
    private float commisionRate;

    @Override
    public double getMonthlysales() {
        return monthlysales;
    }

    @Override
    public void setMonthlysales(double monthlysales) {
        this.monthlysales = monthlysales;
    }

    @Override
    public float getCommisionRate() {
        return commisionRate;
    }

    @Override
    public void setCommisionRate(float commisionRate) {
        this.commisionRate = commisionRate;
    }

    public SalesEmployee(int employeeId, String name, double salary, double msales, float comR) {
        super(employeeId, name,salary);
        setMonthlysales(msales);
        setCommisionRate(comR);
    }

    @Override
    public double calcPay() {
        return getSalary() / 12 + (getMonthlysales() * getCommisionRate());
    }

}
