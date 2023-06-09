package com.example.demo.repository;


import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Address;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository implements AddressRepositoryInterface {

    @Autowired
    private DSLContext dslContext;

    public void insertAddress(Address someAddress) {
        dslContext.insertInto(Tables.ADDRESS, Tables.ADDRESS.EMPID, Tables.ADDRESS.EMPADDRESS)
                .values(someAddress.getEmpid(), someAddress.getEmpaddress());
    }

    public Address getAddressByEmpid(int empid) {
        Address desiredEmployeeAddress = dslContext.selectFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.EMPID.eq(empid))
                .fetchInto(Address.class).get(0);
        return desiredEmployeeAddress;
    }

    public void deleteAddress(int empid) {
        dslContext.deleteFrom(Tables.ADDRESS)
                .where(Tables.ADDRESS.EMPID.eq(empid)).execute();
    }

    public void updateAddress(Address someAddress,int empid) {
        dslContext.update(Tables.ADDRESS).set(Tables.ADDRESS.EMPADDRESS, someAddress.getEmpaddress())
                .where(Tables.ADDRESS.EMPID.eq(empid)).execute();
    }

}
