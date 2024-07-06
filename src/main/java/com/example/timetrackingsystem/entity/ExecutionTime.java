package com.example.timetrackingsystem.entity;

import com.example.timetrackingsystem.enums.ExecutionType;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "execution_time")

public class ExecutionTime extends BaseEntity {

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "execution_time", nullable = false)
    private Long executionTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "execution_type", nullable = false)
    private ExecutionType executionType;
}
