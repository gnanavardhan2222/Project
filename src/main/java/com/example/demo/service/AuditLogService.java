package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AuditLogDTO;
import com.example.demo.entity.AuditLog;
import com.example.demo.mapper.AuditLogMapper;
import com.example.demo.repository.AuditLogRepository;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    public AuditLogService(
            AuditLogRepository auditLogRepository,
            AuditLogMapper auditLogMapper) {

        this.auditLogRepository =
                auditLogRepository;

        this.auditLogMapper =
                auditLogMapper;
    }

    public AuditLogDTO createAuditLog(
            AuditLogDTO dto) {

        AuditLog auditLog =
                auditLogMapper.toEntity(dto);

        AuditLog savedAuditLog =
                auditLogRepository.save(
                        auditLog);

        return auditLogMapper.toDTO(
                savedAuditLog);
    }

    public List<AuditLogDTO>
    getAllAuditLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(auditLogMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AuditLogDTO getAuditLogById(
            Long id) {

        AuditLog auditLog =
                auditLogRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Audit Log Not Found"));

        return auditLogMapper.toDTO(
                auditLog);
    }
}