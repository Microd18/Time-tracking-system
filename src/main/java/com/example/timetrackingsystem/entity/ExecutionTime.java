package com.example.timetrackingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "execution_time")
public class ExecutionTime extends BaseEntity{

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "execution_time")
    private Long executionTime;
}
