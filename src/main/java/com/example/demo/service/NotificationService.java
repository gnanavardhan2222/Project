package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.entity.Notification;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationMapper notificationMapper) {

        this.notificationRepository =
                notificationRepository;

        this.notificationMapper =
                notificationMapper;
    }

    public NotificationDTO createNotification(
            NotificationDTO dto) {

        Notification notification =
                notificationMapper.toEntity(dto);

        Notification savedNotification =
                notificationRepository.save(
                        notification);

        return notificationMapper.toDTO(
                savedNotification);
    }

    public List<NotificationDTO>
    getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(
            Long id) {

        Notification notification =
                notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Notification Not Found"));

        return notificationMapper.toDTO(
                notification);
    }
}