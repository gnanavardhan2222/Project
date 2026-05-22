package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.HolidayCalendarDTO;
import com.example.demo.entity.HolidayCalendar;

@Mapper(componentModel = "spring")

public interface HolidayCalendarMapper {

    HolidayCalendar toEntity(
            HolidayCalendarDTO dto);

    HolidayCalendarDTO toDTO(
            HolidayCalendar entity);

}