package com.example.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDate;
import com.example.airline.model.*;
import com.example.airline.repository.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired private FlightRepository flightRepo;
    @Autowired private ScheduleRepository scheduleRepo;
    
    @PostMapping()
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
        System.out.println("inside");
        try {
            Schedule savedSchedule = scheduleRepo.save(schedule);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
