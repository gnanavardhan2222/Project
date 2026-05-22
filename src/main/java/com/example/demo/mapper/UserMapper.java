package com.example.demo.mapper;


import org.mapstruct.Mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;


@Mapper(componentModel = "spring")

public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);
}
