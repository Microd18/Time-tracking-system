package com.example.timetrackingsystem.entity;

import com.example.timetrackingsystem.enums.ExecutionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "execution_time")
public class ExecutionTime extends BaseEntity {

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "execution_time", nullable = false)
    private Double executionTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "execution_type", nullable = false)
    private ExecutionType executionType;
}
