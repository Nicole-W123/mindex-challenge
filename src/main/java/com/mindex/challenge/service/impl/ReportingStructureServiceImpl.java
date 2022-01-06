package com.mindex.challenge.service.impl;

import java.util.ArrayList;
import java.util.Objects;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure read(String id){
        LOG.debug("Creating reporting structure with id [{}]", id);

        ReportingStructure reportingStructure = new ReportingStructure();
        Employee employee = employeeService.read(id);
        reportingStructure.setEmployee(employee);

        ArrayList<String> allReports = new ArrayList<String>();
        ArrayList<Employee> bfsQueue = new ArrayList<Employee>();

        if(Objects.nonNull(employee.getDirectReports()))
            bfsQueue.addAll(employee.getDirectReports());
        while(!bfsQueue.isEmpty()){
            Employee report = bfsQueue.remove(0);
            
            report = employeeService.read(report.getEmployeeId());
            
            if(Objects.nonNull(report.getDirectReports()))
                bfsQueue.addAll(report.getDirectReports());
            
            if(!allReports.contains(report.getEmployeeId()))
                allReports.add(report.getEmployeeId());
            
        }
        reportingStructure.setNumberOfReports(allReports.size());
        
        return reportingStructure;
    }
}
