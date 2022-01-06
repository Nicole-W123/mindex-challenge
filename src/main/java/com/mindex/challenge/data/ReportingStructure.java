package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){
    }
    
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setNumberOfReports(int numberOfReports){
        this.numberOfReports = numberOfReports;
    }

    public int getNumberOfReports(){
        return numberOfReports;
    }
}
