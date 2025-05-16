package com.example.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.airline.model.*;
import com.example.airline.repository.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired private TicketRepository ticketRepo;
    @Autowired private ScheduleRepository scheduleRepo;

    // @PostMapping
    // public ResponseEntity<?> bookTicket(@RequestBody Ticket ticket) {
    //     if (ticket.getSchedule() == null || scheduleRepo.findById(ticket.getSchedule().getId()).isEmpty()) {
    //         return ResponseEntity.badRequest().body("Invalid Schedule ID");
    //     }
    //     return ResponseEntity.ok(ticketRepo.save(ticket));
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        return ticketRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> cancelTicket(@PathVariable Long id) {
    //     Optional<Ticket> ticketOpt = ticketRepo.findById(id);
    //     if (ticketOpt.isEmpty()) return ResponseEntity.notFound().build();

    //     Ticket ticket = ticketOpt.get();
    //     ticket.setCancelled(true);
    //     ticketRepo.save(ticket);
    //     return ResponseEntity.ok("Ticket cancelled successfully");
    // }
}

