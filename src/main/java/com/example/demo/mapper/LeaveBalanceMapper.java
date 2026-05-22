package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.LeaveBalanceDTO;
import com.example.demo.entity.LeaveBalance;

@Mapper(componentModel = "spring")
public interface LeaveBalanceMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "leaveType.id", target = "leaveTypeId")
    LeaveBalanceDTO toDTO(LeaveBalance leaveBalance);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "leaveTypeId", target = "leaveType.id")
    LeaveBalance toEntity(LeaveBalanceDTO dto);
}