package com.example.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.entity.HolidayCalendar;
import com.example.demo.entity.LeaveBalance;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.LeaveType;
import com.example.demo.entity.User;
import com.example.demo.enums.LeaveStatus;
import com.example.demo.mapper.LeaveRequestMapper;
import com.example.demo.repository.HolidayCalendarRepository;
import com.example.demo.repository.LeaveBalanceRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.LeaveTypeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    private final LeaveRequestMapper leaveRequestMapper;

    private final UserRepository userRepository;

    private final LeaveTypeRepository leaveTypeRepository;

    private final LeaveBalanceRepository leaveBalanceRepository;

    private final HolidayCalendarRepository holidayCalendarRepository;

    public LeaveRequestService(
            LeaveRequestRepository leaveRequestRepository,
            LeaveRequestMapper leaveRequestMapper,
            UserRepository userRepository,
            LeaveTypeRepository leaveTypeRepository,
            LeaveBalanceRepository leaveBalanceRepository,
            HolidayCalendarRepository holidayCalendarRepository) {

        this.leaveRequestRepository =
                leaveRequestRepository;

        this.leaveRequestMapper =
                leaveRequestMapper;

        this.userRepository =
                userRepository;

        this.leaveTypeRepository =
                leaveTypeRepository;

        this.leaveBalanceRepository =
                leaveBalanceRepository;

        this.holidayCalendarRepository =
                holidayCalendarRepository;
    }

    // APPLY LEAVE

    public LeaveRequestDTO applyLeave(
            LeaveRequestDTO dto) {

        User user =
                userRepository.findById(
                        dto.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User Not Found"));

        LeaveType leaveType =
                leaveTypeRepository.findById(
                        dto.getLeaveTypeId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Type Not Found"));

        Integer year =
                dto.getStartDate().getYear();

        LeaveBalance leaveBalance =
                leaveBalanceRepository
                .findByUserIdAndLeaveTypeIdAndYear(
                        dto.getUserId(),
                        dto.getLeaveTypeId(),
                        year)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Balance Not Found"));

        double totalDays =
                calculateLeaveDays(
                        dto.getStartDate(),
                        dto.getEndDate());

        if(dto.isHalfDay()) {

            totalDays = 0.5;
        }

        if(leaveBalance.getRemainingDays()
                < totalDays) {

            throw new RuntimeException(
                    "Insufficient Leave Balance");
        }

        leaveBalance.setUsedDays(
                leaveBalance.getUsedDays()
                + totalDays);

        leaveBalance.setRemainingDays(
                leaveBalance.getRemainingDays()
                - totalDays);

        leaveBalanceRepository.save(
                leaveBalance);

        LeaveRequest leaveRequest =
                leaveRequestMapper.toEntity(dto);

        leaveRequest.setUser(user);

        leaveRequest.setLeaveType(
                leaveType);

        leaveRequest.setTotalDays(
                totalDays);

        leaveRequest.setStatus(
                LeaveStatus.PENDING);

        LeaveRequest savedRequest =
                leaveRequestRepository.save(
                        leaveRequest);

        return leaveRequestMapper.toDTO(
                savedRequest);
    }

    // CALCULATE LEAVE DAYS

    private double calculateLeaveDays(
            LocalDate startDate,
            LocalDate endDate) {

        double days = 0;

        List<HolidayCalendar> holidays =
                holidayCalendarRepository
                .findByHolidayDateBetween(
                        startDate,
                        endDate);

        for(LocalDate date = startDate;
            !date.isAfter(endDate);
            date = date.plusDays(1)) {

            boolean isWeekend =
                    date.getDayOfWeek()
                    == DayOfWeek.SATURDAY
                    ||
                    date.getDayOfWeek()
                    == DayOfWeek.SUNDAY;
            LocalDate currentDate = date;

            boolean isHoliday =
                    holidays.stream()
                    .anyMatch(h ->
                        h.getHolidayDate()
                        .equals(currentDate));

            if(!isWeekend && !isHoliday) {

                days++;
            }
        }

        return days;
    }

    // APPROVE LEAVE

    public LeaveRequestDTO approveLeave(
            Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(
                        leaveRequestId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Request Not Found"));

        leaveRequest.setStatus(
                LeaveStatus.APPROVED);

        LeaveRequest updatedRequest =
                leaveRequestRepository.save(
                        leaveRequest);

        return leaveRequestMapper.toDTO(
                updatedRequest);
    }

    // REJECT LEAVE

    public LeaveRequestDTO rejectLeave(
            Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(
                        leaveRequestId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Request Not Found"));

        leaveRequest.setStatus(
                LeaveStatus.REJECTED);

        LeaveRequest updatedRequest =
                leaveRequestRepository.save(
                        leaveRequest);

        return leaveRequestMapper.toDTO(
                updatedRequest);
    }

    // CANCEL LEAVE

    public LeaveRequestDTO cancelLeave(
            Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(
                        leaveRequestId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Request Not Found"));

        leaveRequest.setStatus(
                LeaveStatus.CANCELLED);

        LeaveRequest updatedRequest =
                leaveRequestRepository.save(
                        leaveRequest);

        return leaveRequestMapper.toDTO(
                updatedRequest);
    }

    // GET EMPLOYEE LEAVES

    public List<LeaveRequestDTO>
    getEmployeeLeaves(Long userId) {

        return leaveRequestRepository
                .findByUserId(userId)
                .stream()
                .map(leaveRequestMapper::toDTO)
                .toList();
    }
}