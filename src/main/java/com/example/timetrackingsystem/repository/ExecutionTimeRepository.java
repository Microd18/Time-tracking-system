package com.example.timetrackingsystem.repository;

import com.example.timetrackingsystem.entity.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Integer> {


}
