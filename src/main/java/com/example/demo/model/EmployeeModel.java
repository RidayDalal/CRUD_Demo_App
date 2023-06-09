package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeModel {

    private int empid;
    private String name;
    private int age;
    private AddressModel addressModel;

}
