package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HolidayCalendarDTO {

    private Long id;

    private String holidayName;

    private LocalDate holidayDate;

    private String type;

    private Long projectId;

}