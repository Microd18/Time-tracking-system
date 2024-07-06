package com.example.timetrackingsystem.repository;

import com.example.timetrackingsystem.entity.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Integer> {

    @Query(value = "SELECT " +
            "m.method_name AS methodName, " +
            "AVG(m.execution_time) AS averageExecutionTime, " +
            "SUM(m.execution_time) AS totalExecutionTime " +
            "FROM execution_time m " +
            "WHERE (?1 IS NULL OR m.method_name ?1) " +
            "GROUP BY m.method_name " +
            "ORDER BY averageExecutionTime",
            nativeQuery = true)
    List<ExecutionTime> findAllExecutionTimeByName(@Param("methodName") String methodName);
}
