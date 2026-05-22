package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.enums.LeaveStatus;

public interface LeaveRequestRepository
extends JpaRepository<LeaveRequest, Long>{

    List<LeaveRequest>
    findByUserId(Long userId);

    List<LeaveRequest>
    findByStatus(LeaveStatus status);
}