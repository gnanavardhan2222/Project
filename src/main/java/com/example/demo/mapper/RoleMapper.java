package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.Role;

@Mapper(componentModel = "spring")

public interface RoleMapper {

    Role toEntity(
            RoleDTO dto);

    RoleDTO toDTO(
            Role entity);

}