package com.example.demo.dto;



import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuditLogDTO {

    private Long id;

    private String entityName;

    private Long entityId;

    private String action;

    private Long performedBy;

    private LocalDateTime createdAt;

}
