package com.example.demo.mapper;



import org.mapstruct.Mapper;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;

@Mapper(componentModel = "spring")

public interface ProjectMapper {

    Project toEntity(
            ProjectDTO dto);

    ProjectDTO toDTO(
            Project entity);

}