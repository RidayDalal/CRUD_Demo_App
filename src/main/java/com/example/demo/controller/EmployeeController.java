package com.example.demo.controller;

import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public String addEmployee(@RequestBody EmployeeModel employee) {
        employeeService.insertEmployeeDetails(employee);
        return "new employee successfully added";
    }

    @GetMapping
    public List<EmployeeModel> getEmployees() {
        return employeeService.getEmployeeDetails();
    }

    @PutMapping
    public String putEmployee(@RequestBody EmployeeModel employee,
                              @RequestParam int employeeId,
                              @RequestParam String newName) {
        employeeService.updateEmployeeDetails(employee, newName, employeeId);
        return "employee record successfully updated";
    }

    @DeleteMapping("{empid}")
    public String deleteEmployee(@PathVariable int empid) {
        employeeService.deleteEmployeeDetails(empid);
        return "employee record successfully deleted";
    }
}
