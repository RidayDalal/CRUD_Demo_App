package com.example.demo.repository;

import com.example.demo.model.EmployeeModel;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Employees;

import java.util.List;

public interface EmployeeRepositoryInterface {

    public void insertEmployee(EmployeeModel employee);
    public List<Employees> getEmployees();
    public void deleteEmployee(int empid);
    public void updateEmployee(EmployeeModel employee, String newName, int employeeId);
}
