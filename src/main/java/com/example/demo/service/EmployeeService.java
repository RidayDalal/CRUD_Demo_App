package com.example.demo.service;

import com.example.demo.model.AddressModel;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.EmployeeRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Address;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Employees;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressService addressService;

    public void insertEmployeeDetails(EmployeeModel employeeModel) {
        Employees employee = new Employees();
        BeanUtils.copyProperties(employeeModel, employee);

        int empid = employeeRepository.insertEmployee(employee).getEmpid();
        Address address = new Address();
        BeanUtils.copyProperties(employeeModel.getAddressModel(), address);
        address.setEmpid(empid);
        addressService.insert(employeeModel.getAddressModel());

    }

    public List<EmployeeModel> getEmployeeDetails() {
        List<Employees> employeeRecords = employeeRepository.getEmployees();
        List<EmployeeModel> listEmployeeModels = new ArrayList<EmployeeModel>();

        for (Employees employee: employeeRecords) {
            EmployeeModel newModel = new EmployeeModel();
            BeanUtils.copyProperties(employee, newModel);
            AddressModel addressModel = addressService.get(employee.getEmpid());

            newModel.setAddressModel(addressModel);
            listEmployeeModels.add(newModel);
        }
        return listEmployeeModels;
    }

    public void deleteEmployeeDetails(int employeeId) {
        addressService.delete(employeeId);
        employeeRepository.deleteEmployee(employeeId);
    }

    public void updateEmployeeDetails(EmployeeModel employee, String newName, int employeeId) {
        Employees someEmployee = new Employees();
        BeanUtils.copyProperties(employee, someEmployee);
        employeeRepository.updateEmployee(someEmployee, newName, employeeId);
    }
}
