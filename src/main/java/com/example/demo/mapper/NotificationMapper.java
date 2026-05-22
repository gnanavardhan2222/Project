package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.entity.Notification;

@Mapper(componentModel = "spring")

public interface NotificationMapper {

    Notification toEntity(
            NotificationDTO dto);

    NotificationDTO toDTO(
            Notification entity);

}