package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.AuditLogDTO;
import com.example.demo.entity.AuditLog;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

    @Mapping(source = "performedBy.id",
            target = "performedBy")
    AuditLogDTO toDTO(AuditLog auditLog);

    @Mapping(source = "performedBy",
            target = "performedBy.id")
    AuditLog toEntity(AuditLogDTO dto);
}