package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.LeaveTypeDTO;
import com.example.demo.entity.LeaveType;

@Mapper(componentModel = "spring")

public interface LeaveTypeMapper {

    LeaveType toEntity(
            LeaveTypeDTO dto);

    LeaveTypeDTO toDTO(
            LeaveType entity);

}