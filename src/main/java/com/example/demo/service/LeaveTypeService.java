package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveTypeDTO;
import com.example.demo.entity.LeaveType;
import com.example.demo.mapper.LeaveTypeMapper;
import com.example.demo.repository.LeaveTypeRepository;

@Service
public class LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveTypeMapper leaveTypeMapper;

    public LeaveTypeService(
            LeaveTypeRepository leaveTypeRepository,
            LeaveTypeMapper leaveTypeMapper) {

        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveTypeMapper = leaveTypeMapper;
    }

    public LeaveTypeDTO createLeaveType(
            LeaveTypeDTO dto) {

        LeaveType leaveType =
                leaveTypeMapper.toEntity(dto);

        LeaveType savedLeaveType =
                leaveTypeRepository.save(leaveType);

        return leaveTypeMapper.toDTO(
                savedLeaveType);
    }

    public List<LeaveTypeDTO>
    getAllLeaveTypes() {

        return leaveTypeRepository.findAll()
                .stream()
                .map(leaveTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LeaveTypeDTO getLeaveTypeById(
            Long id) {

        LeaveType leaveType =
                leaveTypeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Type Not Found"));

        return leaveTypeMapper.toDTO(
                leaveType);
    }
}
