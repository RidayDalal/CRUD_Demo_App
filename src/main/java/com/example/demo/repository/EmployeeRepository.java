package com.example.demo.repository;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Employees;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private DSLContext dslContext;

    public Employees insertEmployee(Employees employee) {
        dslContext.insertInto(Tables.EMPLOYEES, Tables.EMPLOYEES.NAME, Tables.EMPLOYEES.EMPID, Tables.EMPLOYEES.AGE)
                .values(employee.getName(), employee.getEmpid(), employee.getAge()).execute();
        return employee;
    }

    public List<Employees> getEmployees() {
        return dslContext.selectFrom(Tables.EMPLOYEES)
                .fetchInto(Employees.class);
    }

    public void deleteEmployee(int empid) {
        dslContext.deleteFrom(Tables.EMPLOYEES).
                where(Tables.EMPLOYEES.EMPID.eq(empid)).execute();
    }

    public void updateEmployee(Employees employee, String newName, int employeeId) {
        dslContext.update(Tables.EMPLOYEES).set(Tables.EMPLOYEES.NAME, newName)
                .where(Tables.EMPLOYEES.EMPID.eq(employeeId)).execute();
    }
}
