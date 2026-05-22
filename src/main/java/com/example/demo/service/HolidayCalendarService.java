package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.HolidayCalendarDTO;
import com.example.demo.entity.HolidayCalendar;
import com.example.demo.mapper.HolidayCalendarMapper;
import com.example.demo.repository.HolidayCalendarRepository;

@Service
public class HolidayCalendarService {

    private final HolidayCalendarRepository holidayCalendarRepository;
    private final HolidayCalendarMapper holidayCalendarMapper;

    public HolidayCalendarService(
            HolidayCalendarRepository holidayCalendarRepository,
            HolidayCalendarMapper holidayCalendarMapper) {

        this.holidayCalendarRepository =
                holidayCalendarRepository;

        this.holidayCalendarMapper =
                holidayCalendarMapper;
    }

    public HolidayCalendarDTO addHoliday(
            HolidayCalendarDTO dto) {

        HolidayCalendar holiday =
                holidayCalendarMapper.toEntity(dto);

        HolidayCalendar savedHoliday =
                holidayCalendarRepository.save(
                        holiday);

        return holidayCalendarMapper.toDTO(
                savedHoliday);
    }

    public List<HolidayCalendarDTO>
    getAllHolidays() {

        return holidayCalendarRepository.findAll()
                .stream()
                .map(holidayCalendarMapper::toDTO)
                .collect(Collectors.toList());
    }

    public HolidayCalendarDTO getHolidayById(
            Long id) {

        HolidayCalendar holiday =
                holidayCalendarRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Holiday Not Found"));

        return holidayCalendarMapper.toDTO(
                holiday);
    }
}