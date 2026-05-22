package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(
            ProjectRepository projectRepository,
            ProjectMapper projectMapper) {

        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectDTO createProject(
            ProjectDTO dto) {

        Project project =
                projectMapper.toEntity(dto);

        Project savedProject =
                projectRepository.save(project);

        return projectMapper.toDTO(
                savedProject);
    }

    public List<ProjectDTO> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(
            Long id) {

        Project project =
                projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project Not Found"));

        return projectMapper.toDTO(project);
    }
}