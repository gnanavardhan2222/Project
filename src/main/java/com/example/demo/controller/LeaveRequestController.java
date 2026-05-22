package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin("*")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(
            LeaveRequestService leaveRequestService) {

        this.leaveRequestService =
                leaveRequestService;
    }

    // APPLY LEAVE

    @PostMapping("/apply")
    public ResponseEntity<LeaveRequestDTO>
    applyLeave(
            @RequestBody LeaveRequestDTO dto) {

        return ResponseEntity.ok(
                leaveRequestService
                .applyLeave(dto));
    }

    // APPROVE LEAVE

    @PutMapping("/approve/{id}")
    public ResponseEntity<LeaveRequestDTO>
    approveLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService
                .approveLeave(id));
    }

    // REJECT LEAVE

    @PutMapping("/reject/{id}")
    public ResponseEntity<LeaveRequestDTO>
    rejectLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService
                .rejectLeave(id));
    }

    // CANCEL LEAVE

    @PutMapping("/cancel/{id}")
    public ResponseEntity<LeaveRequestDTO>
    cancelLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService
                .cancelLeave(id));
    }

    // GET EMPLOYEE LEAVES

    @GetMapping("/employee/{userId}")
    public ResponseEntity<List<LeaveRequestDTO>>
    getEmployeeLeaves(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                leaveRequestService
                .getEmployeeLeaves(userId));
    }
}