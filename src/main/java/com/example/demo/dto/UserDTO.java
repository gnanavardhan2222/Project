package com.example.demo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private Long roleId;

    private Long managerId;

    private Long departmentId;

    private Long projectId;
}