package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            DepartmentMapper departmentMapper) {

        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO createDepartment(
            DepartmentDTO dto) {

        Department department =
                departmentMapper.toEntity(dto);

        Department savedDepartment =
                departmentRepository.save(department);

        return departmentMapper.toDTO(
                savedDepartment);
    }

    public List<DepartmentDTO>
    getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(
            Long id) {

        Department department =
                departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department Not Found"));

        return departmentMapper.toDTO(
                department);
    }
}