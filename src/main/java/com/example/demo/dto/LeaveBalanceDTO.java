package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveBalanceDTO {

    private Long id;

    private Long userId;

    private Long leaveTypeId;

    private Double totalDays;

    private Double usedDays;

    private Double remainingDays;

    private Integer year;
}