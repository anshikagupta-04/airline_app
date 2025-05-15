package com.example.airline.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airline.model.Schedule;
import java.util.*;
import java.time.*;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByFlightIdAndDate(Long flightId, LocalDate date);
}

