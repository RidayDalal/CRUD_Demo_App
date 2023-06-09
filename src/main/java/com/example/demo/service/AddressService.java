package com.example.demo.service;

import com.example.demo.model.AddressModel;
import com.example.demo.repository.AddressRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void insert(AddressModel someAddress) {
        Address address = new Address();
        BeanUtils.copyProperties(someAddress, address);
        addressRepository.insertAddress(address);
    }

    public AddressModel get(int empid) {
        Address someAddress = addressRepository.getAddressByEmpid(empid);
        AddressModel addressModel = new AddressModel();
        BeanUtils.copyProperties(someAddress, addressModel);
        return addressModel;
    }

    public void delete(int employeeId) {
        addressRepository.deleteAddress(employeeId);
    }

    public void update(AddressModel addressModel, int empid) {
        Address someAddress = addressRepository.getAddressByEmpid(empid);
        BeanUtils.copyProperties(someAddress, addressModel);
        addressRepository.updateAddress(someAddress, empid);
    }

}
