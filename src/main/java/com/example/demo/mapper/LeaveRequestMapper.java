package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.entity.LeaveRequest;

@Mapper(componentModel = "spring")

public interface LeaveRequestMapper {

    LeaveRequest toEntity(
            LeaveRequestDTO dto);

    LeaveRequestDTO toDTO(
            LeaveRequest entity);

}