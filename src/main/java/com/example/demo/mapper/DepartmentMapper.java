package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;

@Mapper(componentModel = "spring")

public interface DepartmentMapper {

    Department toEntity(
            DepartmentDTO dto);

    DepartmentDTO toDTO(
            Department entity);

}