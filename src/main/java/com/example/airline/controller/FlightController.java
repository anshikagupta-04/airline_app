package com.example.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDate;
import com.example.airline.model.*;
import com.example.airline.repository.*;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired private FlightRepository flightRepo;
    @Autowired private ScheduleRepository scheduleRepo;

    @PostMapping()
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        try {
            Flight savedFlight = flightRepo.save(flight);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) String sort) {
        try {
            List<Flight> flightList = new ArrayList<>();
            flightList = sort != null && sort.equalsIgnoreCase("asc")
            ? flightRepo.findAll(Sort.by("id").ascending())
            : flightRepo.findAll();

            if (flightList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(flightList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id) {
        try {
            return flightRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/schedules")
    public List<Schedule> getSchedules(@PathVariable Long id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleRepo.findByFlightIdAndDate(id, date);
    }
}

