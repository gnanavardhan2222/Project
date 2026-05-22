package com.example.demo.dto;


import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LeaveRequestDTO {

    private Long userId;

    private Long leaveTypeId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;
    private boolean halfDay;
}
