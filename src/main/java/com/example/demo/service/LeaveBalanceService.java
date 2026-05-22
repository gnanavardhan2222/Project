package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveBalanceDTO;
import com.example.demo.entity.LeaveBalance;
import com.example.demo.mapper.LeaveBalanceMapper;
import com.example.demo.repository.LeaveBalanceRepository;

@Service
public class LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveBalanceMapper leaveBalanceMapper;

    public LeaveBalanceService(
            LeaveBalanceRepository leaveBalanceRepository,
            LeaveBalanceMapper leaveBalanceMapper) {

        this.leaveBalanceRepository = leaveBalanceRepository;
        this.leaveBalanceMapper = leaveBalanceMapper;
    }

    public LeaveBalanceDTO allocateLeave(
            LeaveBalanceDTO dto) {

        LeaveBalance leaveBalance =
                leaveBalanceMapper.toEntity(dto);

        LeaveBalance savedLeaveBalance =
                leaveBalanceRepository.save(
                        leaveBalance);

        return leaveBalanceMapper.toDTO(
                savedLeaveBalance);
    }

    public List<LeaveBalanceDTO>
    getAllLeaveBalances() {

        return leaveBalanceRepository.findAll()
                .stream()
                .map(leaveBalanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LeaveBalanceDTO getLeaveBalanceById(
            Long id) {

        LeaveBalance leaveBalance =
                leaveBalanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Balance Not Found"));

        return leaveBalanceMapper.toDTO(
                leaveBalance);
    }
}