package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeaveBalance;


public interface LeaveBalanceRepository
extends JpaRepository<LeaveBalance, Long>{

    Optional<LeaveBalance>
    findByUserIdAndLeaveTypeIdAndYear(
            Long userId,
            Long leaveTypeId,
            Integer year);
}
